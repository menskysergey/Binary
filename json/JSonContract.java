package json;

public class JSonContract {
    String barrier;
    Currencies currencies;
    String contract;

    public JSonContract(String barrier, Currencies currencies) {
        this.barrier = barrier;
        this.currencies = currencies;
        this.contract="{\n" +
                "  \"proposal\": 1,\n" +
                "  \"amount\": \"100\",\n" +
                "  \"basis\": \"payout\",\n" +
                "  \"contract_type\": \"NOTOUCH\",\n" +
                "  \"currency\": \"USD\",\n" +
                "  \"duration\": \"1\",\n" +
                "  \"duration_unit\": \"d\",\n" +
                "  \"barrier\": "+barrier+",\n"+
                "  \"symbol\": "+currencies.getSymbol()+"\n" +
                "}";
           }
}
