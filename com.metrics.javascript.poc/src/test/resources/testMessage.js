var doFilter=false;
var tx = request.body;

if(tx) {
        var messageType = String(tx.messageTypeId);
        var txType = tx.transactionType;

        switch (messageType) {
                case String(101):
                        var countryVal=tx.customerCountryCode;
                        doFilter=(txType =="111" && (countryVal=="CH" || countryVal=="CZ" || countryVal=="RO"));
                        break;
                case String(102):
                        doFilter=(txType!= "11111" && txType!= "22222" && txType!= "33333");
                        break;
                case String(103):
                        break;
                case String(104):
                        doFilter=true;  // WHITELIST FILTERING!
                        if(tx.counterpartyBank && (tx.counterpartyBank.startsWith("TEST1") ||
                                tx.counterpartyBank.startsWith("TEST2"))) {
                                doFilter=false;
                        }
                        break;
                default:
                        doFilter=false;

        }

}
result=doFilter;