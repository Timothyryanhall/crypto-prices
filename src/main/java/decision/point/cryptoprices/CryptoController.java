package decision.point.cryptoprices;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CryptoController {

    private static final String COMMA_DELIMITER = ",";

    @GetMapping("/prices")
    public String prices(Model model) throws IOException {
        List<List<String>> records = readCsv();
        List<String> dates = new ArrayList<>();
        List<String> btc = new ArrayList<>();
        List<String> eth = new ArrayList<>();
        List<String> ltc = new ArrayList<>();
        List<String> bch = new ArrayList<>();

        for (int i = 1; i < records.size(); i++) {
            dates.add(records.get(i).get(0));
            btc.add(records.get(i).get(1));
            eth.add(records.get(i).get(2));
            ltc.add(records.get(i).get(3));
            bch.add(records.get(i).get(4));
        }

        model.addAttribute("dates", dates);
        model.addAttribute("btc", btc);
        model.addAttribute("eth", eth);
        model.addAttribute("ltc", ltc);
        model.addAttribute("bch", bch);

        return "prices";
    }

    public List<List<String>> readCsv() throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/crypto-prices.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }
}

