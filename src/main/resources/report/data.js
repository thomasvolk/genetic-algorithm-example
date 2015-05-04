<%
def toJson(wagon) {
  def reihen = []
  def aktuelleReihe = []
  def aktuelleReiheNummer = 0
  wagon.sitzplatzVergabeListe.each { sv ->
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
       reihe.collect { sv ->
           [ sid: sv.sitzplatz.nummer,
              pid: sv.passagier.id,
              fr: sv.sitzplatz.inFahrtrichtung,
              a: sv.sitzplatz.abteil,
              z: sv.zufriedenheitFaktor
            ]
       }
    }  )
  }
  jb.toPrettyString()
}
%>
data = {};
data.startWagon=<%=toJson(startWagon)%>;
<% generation.wagonBelegungen.eachWithIndex { w, i ->
  println "data.wagon_$i=" + toJson(w)
} %>;
