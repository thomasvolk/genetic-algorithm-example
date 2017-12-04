<%
def toJson(wagonBelegung) {
  def wagon = wagonBelegung.wagon
  def reiheAuffuellen = { r ->
    def dummy = { n-> [sid: -1, p: n] }
    if(r) {
      if(r.first().p > 0)  {
        ((r.first().p - 1)..0).each { i -> r.add(i, dummy(i)) }
      }
      def last = wagon.width - 1
      if(r.last().p < last) {
        ((r.last().p + 1)..last).each { i -> r.add(dummy(i)) }
      }
    }
    r
  }
  def rows = []
  def aktuelleReihe = []
  def aktuelleReiheNummer = 0
  wagonBelegung.sitzplatzVergabeListe.each { sv ->
    if(sv.sitzplatz.row != aktuelleReiheNummer && aktuelleReihe) {
      rows << aktuelleReihe
      aktuelleReihe = []
      aktuelleReiheNummer = sv.sitzplatz.row
    }
    aktuelleReihe << sv
  }
  rows << aktuelleReihe
  def jb = new groovy.json.JsonBuilder()
  jb.wagon {
    sitzPlaetze( rows.collect { reihe ->
       reiheAuffuellen(reihe.collect { sv ->
           [ sid: sv.sitzplatz.number,
              pid: sv.passagier.id,
              fr: sv.sitzplatz.inFahrtrichtung,
              a: sv.sitzplatz.cabin,
              z: sv.happinessFaktor,
              p: sv.sitzplatz.position
            ]
       })
    }  )
    werte {
         happiness(wagonBelegung.happiness)
         maximaleHappiness(wagonBelegung.maximaleHappiness)
      }
  }
  jb.toPrettyString()
}
%>
data = {};
data.startWagon=<%=toJson(ctx.startWagonAllocation)%>;

<% if(ctx.generation) { %>

<% ctx.generation.wagonAllocations.eachWithIndex { w, i ->
  println "data.wagon_$i=" + toJson(w)
} %>;
data.bestWagon=<%=toJson(ctx.generation.bestWagonAllocation)%>

<% } /* if */ %>