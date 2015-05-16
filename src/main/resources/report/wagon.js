wagon = {}
wagon.zeichnen = function(canvasId, startX, startY, wagon) {
    var sitzPlaetze = wagon.wagon.sitzPlaetze;
    var reihen = sitzPlaetze.length;
    var plaetze = reihen > 0 ? sitzPlaetze[0].length : 0;

    var canvas = document.getElementById(canvasId);
    var context = canvas.getContext('2d');

    var kabine = 100;
    var xbase = startX + kabine;
    var ybase = startY;

    var border = 2
    var padding = 5;
    var recSize = 35;
    var space = 25;
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
        if(sitzPlatz.z >= 0) {
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
          context.fillStyle = '#666666';
          context.strokeStyle = '#666666';
          var centerOffset = (recSize/2);
          var xOffset = sitzPlatz.fr ? recSize : 0;
          var arcOffset = sitzPlatz.fr ? 0.5 : 0;
          context.moveTo(x + xOffset, y);
          context.arc(x + xOffset, y, padding*3, arcOffset * Math.PI, (0.5 + arcOffset) * Math.PI);
          context.fill();
          context.moveTo(x + xOffset, y + recSize);
          context.arc(x + xOffset, y + recSize, padding*3, (1.5 - arcOffset) * Math.PI, (arcOffset * 3) * Math.PI);
          context.fill();
          context.moveTo(x + xOffset, y);
          context.quadraticCurveTo(x + centerOffset, y + centerOffset, x + xOffset, y + recSize);
          context.stroke();
          context.fillStyle = '#666666';
          context.fill();
          // nummer
          context.beginPath();
          context.font = '7pt Calibri';
          context.textAlign = 'center';
          context.fillStyle = '#000000';
          var xTextOffset = sitzPlatz.fr ? 0 : padding;
          context.fillText(sitzPlatz.pid, x + xTextOffset + padding * 3, y + padding * 4);
        }
      }
    }
}
