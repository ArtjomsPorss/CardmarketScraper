var o = AJAXResponse.getValue(e, "rows", "b64");

AJAXResponse = {
    getValue: function(e, t, n) {
        var r = ""
          , i = e.getElementsByTagName(t);
        if (i && i[0]) {
            for (var o = i[0].childNodes.length, s = 0; s < o; s++)
                r += e.getElementsByTagName(t)[0].childNodes[s].nodeValue;
            switch (n) {
            case "b64":
                r = Base64.decode(r);
                break;
            case "int":
                r = parseInt(r);
                break;
            case "str":
                break;
            default:
                console.log("Error: Invalid Type " + n)
            }
        }
        return r
    },
    showResultMessage: function(e) {
        var t = this.getValue(e, "resultsMessage", "b64");
        if (t) {
            var n = this.getValue(e, "resultType", "b64")
              , r = "";
            switch (n) {
            case "":
                r = "warning";
                break;
            case "error":
                r = "danger";
                break;
            default:
                r = n
            }
            Messenger.alert(t, r)
        }
    }
}


Base64 = {
    decode: function(e) {
        var t = ""
          , n = void 0
          , r = void 0
          , i = void 0
          , o = void 0
          , s = void 0
          , a = void 0
          , l = 0;
        for (e = e.replace(/[^A-Za-z0-9+/=]/g, ""); l < e.length; )
            n = this._keyStr.indexOf(e.charAt(l++)) << 2 | (o = this._keyStr.indexOf(e.charAt(l++))) >> 4,
            r = (15 & o) << 4 | (s = this._keyStr.indexOf(e.charAt(l++))) >> 2,
            i = (3 & s) << 6 | (a = this._keyStr.indexOf(e.charAt(l++))),
            t += String.fromCharCode(n),
            64 !== s && (t += String.fromCharCode(r)),
            64 !== a && (t += String.fromCharCode(i));
        return t = Base64._utf8_decode(t)
    }
}