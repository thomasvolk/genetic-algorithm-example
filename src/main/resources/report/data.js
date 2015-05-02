data = {}
data.startWagon=<%
            def jb = new groovy.json.JsonBuilder()
            jb.wagon {
              sitzPlaetze( startWagon.sitzplatzVergabeListe.collect { sv -> [ id: sv.sitzplatz.nummer ] }  )
            }
            print jb.toPrettyString()
%>