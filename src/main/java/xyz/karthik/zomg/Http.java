package xyz.karthik.zomg;


import com.google.gson.Gson;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.put;

/**
 * Created by vk on 5/2/2017.
 */
public class Http {
    private static final String URL_PATH_KEY = ":key";
    private static final String URL = "/k/" + URL_PATH_KEY;

    public static void main(String[] args) {
        get(URL, (req, res) -> {
            String key = req.params(URL_PATH_KEY);
            String val = Zomg.get(key);

            if (val != null) {
                return asJson(RespModel.aValue(val));
            } else {
                res.status(404); // 404 Not found
                return asJson(RespModel.anErr("not found"));
            }
        });

        put(URL, (req, res) -> {
            String key = req.params(URL_PATH_KEY);
            String val = req.body();
            Zomg.put(key, val);
            return asJson(RespModel.aValue(null));
        });

        after(URL, (req, res) -> {
            res.type("application/json");
        });
    }

    private static final String asJson(Object o) {
        return new Gson().toJson(o);
    }

    private static class RespModel {
        private boolean success;
        private String value;
        private String err;

        public RespModel(boolean success, String value, String err) {
            this.success = success;
            this.value = value;
            this.err = err;
        }

        public static RespModel aValue(String value) {
            return new RespModel(true, value, null);
        }

        public static RespModel anErr(String err) {
            return new RespModel(false, null, err);
        }
    }

}
