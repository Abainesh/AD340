package com.example.hw6;

//JSON to Java class
//http://java2novice.com/java-json/jackson/json-to-java-object/
public class TrafficCamera {
        private String id;
        private String location;
        private String url;
        private String type;

        public TrafficCamera (String id, String description, String url, String type) {
            this.id = id;
            this.location = description;
            this.type = type;
            setUrl(url);
        }

        private void setUrl(String url) {
            String SDOTBaseUrl = "http://www.seattle.gov/trafficcams/images/";
            String WSDOTBaseUrl = "http://images.wsdot.wa.gov/nw/";
            if (type.equals("sdot")) {
                this.url = SDOTBaseUrl + url;
            } else if (type.equals("wsdot")) {
                this.url = WSDOTBaseUrl + url;
            }
        }

        public String getUrl() {
            return this.url;
        }

        public String getLocation() {
            return this.location;
        }

    }
