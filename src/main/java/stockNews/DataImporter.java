package stockNews;

import java.io.IOException;
import java.util.List;

public interface DataImporter {
    void importData() throws IOException;

    List<String[]> parse() throws IOException;
}
