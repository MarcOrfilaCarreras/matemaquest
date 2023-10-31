package cloud.marcorfilacarreras.matemaquest.libsql;

import java.util.ArrayList;
import java.util.List;

public class LibsqlResultSet {
  public List<String> columns;
  public List<Row> rows;

  public static class Row extends ArrayList<Object> {
    public int getInt(int columnIdx) {
      return (int) get(columnIdx);
    }

    public String getString(int columnIdx) {
      return (String) get(columnIdx);
    }
    
    public double getDouble(int columnIdx) {
      return (double) get(columnIdx);
    }
  }
}
