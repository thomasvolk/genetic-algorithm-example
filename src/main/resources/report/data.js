<%
def toJson(wagon) {
  def jb = new groovy.json.JsonBuilder()
  jb.wagon {
    sitzPlaetze( wagon.sitzplatzVergabeListe.collect {
      sv -> [ sid: sv.sitzplatz.nummer,
              pid: sv.passagier.id,
              fr: sv.sitzplatz.inFahrtrichtung,
              a: sv.sitzplatz.abteil,
              z: sv.zufriedenheitFaktor
            ]
    }  )
  }
  jb.toPrettyString()
}
%>
data = {}
data.startWagon=<%=toJson(startWagon)%>
<% generation.wagons.eachWithIndex { w, i ->
  println "data.generation_$i=" + toJson(w)
} %>

 var sitzPlaetze = [
        [{id: 1, fr: true, a: false, z: 0.8}, {id: 2, fr: true, a: false, z: 0.6}, {id: 3, fr: true, a: false, z: 0}, {id: 4, fr: true, a: false, z: 1.0}],
        [{id: 5, fr: true, a: false, z: 1.0}, {id: 6, fr: true, a: false, z: 1.0}, {id: 7, fr: true, a: false, z: 1.0}, {id: 8, fr: true, a: false, z: 1.0}],
        [{id: 9, fr: false, a: false, z: -1}, {id:10, fr: false, a: false, z: 1.0}, {id:11, fr: false, a: false, z: 1.0}, {id:12, fr: false, a: false, z: 1.0}],
        [{id:13, fr: false, a: false, z: -1}, {id:14, fr: false, a: false, z: 1.0}, {id:15, fr: false, a: false, z: 1.0}, {id:16, fr: false, a: false, z: 1.0}],
        [{id:-1}, {id:18, fr: false, a: true, z: 1.0}, {id:19, fr: false, a: true, z: 0.9}, {id:20, fr: false, a: true, z: 0.2}],
        [{id:-1}, {id:22, fr: true, a: true, z: 1.0}, {id:23, fr: true, a: true, z: 0.4}, {id:124, fr: true, a: true, z: 1.0}]
      ];