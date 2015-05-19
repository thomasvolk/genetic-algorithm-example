<%
def toJson(wagonBelegung) {
  def wagon = wagonBelegung.wagon
  def reiheAuffuellen = { r ->
    def dummy = { n-> [sid: -1, p: n] }
    if(r) {
      if(r.first().p > 0)  {
        ((r.first().p - 1)..0).each { i -> r.add(i, dummy(i)) }
      }
      def last = wagon.breite - 1
      if(r.last().p < last) {
        ((r.last().p + 1)..last).each { i -> r.add(dummy(i)) }
      }
    }
    r
  }
  def reihen = []
  def aktuelleReihe = []
  def aktuelleReiheNummer = 0
  wagonBelegung.sitzplatzVergabeListe.each { sv ->
    if(sv.sitzplatz.reihe != aktuelleReiheNummer && aktuelleReihe) {
      reihen << aktuelleReihe
      aktuelleReihe = []
      aktuelleReiheNummer = sv.sitzplatz.reihe
    }
    aktuelleReihe << sv
  }
  reihen << aktuelleReihe
  def jb = new groovy.json.JsonBuilder()
  jb.wagon {
    sitzPlaetze( reihen.collect { reihe ->
       reiheAuffuellen(reihe.collect { sv ->
           [ sid: sv.sitzplatz.nummer,
              pid: sv.passagier.id,
              fr: sv.sitzplatz.inFahrtrichtung,
              a: sv.sitzplatz.abteil,
              z: sv.zufriedenheitFaktor,
              p: sv.sitzplatz.position
            ]
       })
    }  )
    werte {
         zufriedenheit(wagonBelegung.zufriedenheit)
         maximaleZufriedenheit(wagonBelegung.maximaleZufriedenheit)
      }
  }
  jb.toPrettyString()
}
%>
data = {};
data.startWagon=<%=toJson(ctx.startWagonBelegung)%>;
<% ctx.generation.wagonBelegungen.eachWithIndex { w, i ->
  println "data.wagon_$i=" + toJson(w)
} %>;
data.besterWagon=<%=toJson(ctx.generation.besteWagonBelegung)%>