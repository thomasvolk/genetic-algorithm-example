
wagon = {};
wagon.zeichnen = function(canvasId, startX, startY, wagonDaten, inModus) {
    var modus = inModus || "highlight";
    var sitzPlaetze = wagonDaten.wagon.sitzPlaetze;
    var reihen = sitzPlaetze.length;
    var plaetze = reihen > 0 ? sitzPlaetze[0].length : 0;

    var canvas = document.getElementById(canvasId);
    var context = canvas.getContext('2d');

    var kabine = 100;
    var xbase = startX + kabine;
    var ybase = startY;

    var border = 2
    var padding = 5;
    var recSize = 30;
    var space = 20;
    var recSpaceY = recSize + padding;
    var recSpaceX = recSize + space + padding;

    var width = (reihen * recSpaceX) + padding;
    var height = (plaetze * recSpaceY) + padding;

    context.beginPath();
    context.rect(xbase, ybase, width, height);
    context.moveTo(xbase, ybase);
    context.quadraticCurveTo(startX - kabine, height/2, xbase, height);

    context.fillStyle = '#e2e2e2';
    context.fill();
    context.lineWidth = border;
    context.strokeStyle = '#666666';
    context.stroke();


    for(var r = 0; r < sitzPlaetze.length; r++) {
      var reihe = sitzPlaetze[r];
      for(var p = 0; p < reihe.length; p++) {
        var sitzPlatz = reihe[p];
        sitzFarbe = '#fff'
        if(sitzPlatz.z >= 0 && modus == "highlight") {
          var red = parseInt(( 1.0 - sitzPlatz.z) * 255);
          var green = parseInt(sitzPlatz.z * 255);
          sitzFarbe = 'rgb(' + red +  ', ' + green + ', 0)';
        }
        if(sitzPlatz.sid > 0) {
          var recX = xbase + (r*recSpaceX);
          var recY = ybase + ((reihe.length - 1) * recSpaceY) - (p*recSpaceY);
          if(sitzPlatz.a) {
            // abteil
            context.beginPath();
            var p2 = padding/2;
            context.rect(recX + p2, recY + p2, recSpaceX, recSpaceY);
            context.lineWidth = 1;
            context.strokeStyle = '#aaa';
            context.stroke();
            context.fillStyle = '#aaa';
            context.fill();
          }
          // sitzPlatz
          context.beginPath();
          var xSpace = sitzPlatz.fr ? space : 0;
          var x = recX + xSpace + padding;
          var y = recY + padding;
          context.beginPath();
          context.rect(x, y, recSize, recSize);
          context.lineWidth = 1;
          context.strokeStyle = sitzFarbe;
          context.stroke();
          context.fillStyle = sitzFarbe;
          context.fill();
          // polster
          context.beginPath();
          context.fillStyle = '#000';
          context.strokeStyle = '#000';
          var xOffset = sitzPlatz.fr ? recSize - border: 0;
          var xOffsetArm = sitzPlatz.fr ? (recSize / 2) - border: 0;
          context.rect(x + xOffset, y, border, recSize);
          context.rect(x + xOffsetArm, y, recSize / 2, border);
          context.rect(x + xOffsetArm, y + recSize - border, recSize / 2, border);
          context.stroke();
          context.fillStyle = '#000';
          context.fill();
          // Passagier Id
          if(modus == "highlight") {
              context.beginPath();
              context.font = '6pt Calibri';
              context.textAlign = 'center';
              context.fillStyle = '#000000';
              var xTextOffset = sitzPlatz.fr ? 0 : padding;
              context.fillText(sitzPlatz.pid, x + xTextOffset + padding * 3, y + padding * 3.5);
          }
        }
      }
    }
}
