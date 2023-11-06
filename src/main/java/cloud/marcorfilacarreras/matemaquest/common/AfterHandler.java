package cloud.marcorfilacarreras.matemaquest.common;

import org.json.JSONObject;
import org.json.XML;
import spark.Filter;
import spark.Request;
import spark.Response;

public class AfterHandler implements Filter {

    @Override
    public void handle(Request request, Response response) {
        if (request.queryMap("format").hasValue() && request.queryMap("format").value().equals("xml")){
            // Convert JSON to XML
            JSONObject jsonObject = new JSONObject(response.body());
            response.body("<?xml version=\"1.0\" encoding=\"UTF-8\"?><root>" + XML.toString(jsonObject) + "</root>");
            response.type("application/xml");
        }
    }
}
