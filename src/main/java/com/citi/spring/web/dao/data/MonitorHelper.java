package com.citi.spring.web.dao.data;

import com.citi.spring.web.dao.entity.Monitor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MonitorHelper {

    private SSLSocketFactory socketFactory() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
                //return new X509Certificate[0];
            }

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }
        }};
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        return sc.getSocketFactory();
    }

    public ArrayList<Monitor> getUrlEntityFromDisk(String path) {

        FileInputStream fis = null;
        BufferedReader reader = null;
        ArrayList<Monitor> results = new ArrayList<Monitor>();
        try {
            fis = new FileInputStream(path);
            reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            while (line != null) {

                if (line.length() > 0) {
                    Monitor monitor = new Monitor();
                    String[] parts = line.split("\\|");
                    monitor.setName(parts[1]);
                    monitor.setEnv(parts[3]);
                    monitor.setHostname(parts[4]);
                    monitor.setLink(parts[0]);
                    results.add(monitor);

                }

                line = reader.readLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found exception");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Input output exception not found exception");
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Cause of exception is  " + ex.getCause());

            }
        }
        return results;
    }

    public ArrayList<Monitor> getUrlEntityFromDiskClass(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        ArrayList<Monitor> results = new ArrayList<Monitor>();
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    String[] parts = line.split("\\|");
                    Monitor monitor = new Monitor();

                    monitor.setName(parts[1]);
                    monitor.setEnv(parts[3]);
                    monitor.setHostname(parts[4]);
                    monitor.setLink(parts[0]);
                    results.add(monitor);

                }
            }
            scanner.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return results;
    }

    public ArrayList<String> isHealthy(String urlString) {

        ArrayList<String> list = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            int code = -1;
            if (url.getProtocol().toLowerCase().equals("https")) {

                // Create all-trusting hostname verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                SSLSocketFactory sslSocketFactory = socketFactory();
                HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);

                // Install the all-trusting hostname verifier
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                long startTime = System.currentTimeMillis();
                connection.connect();
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
                code = connection.getResponseCode();
                list.add(elapsedTime+"");
            } else {

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                long startTime = System.currentTimeMillis();
                connection.connect();
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Total elapsed http request/response time in milliseconds: " + elapsedTime);
                code = connection.getResponseCode();
                list.add(elapsedTime+"");
            }


            if (code == 200 || code == 401 || code == 302) {
                list.add("true");
                return list;
            }
        } catch (IOException e) {
            if(list.isEmpty())list.add("No Time");
            list.add("false");
            return list;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(list.isEmpty())list.add("No Time");
        list.add("false");
        return list;
    }


    public boolean dbValidator(String dbURI) {
        if (dbURI.contains("mongodb")) {
            MongoClientURI connectionString = new MongoClientURI(dbURI);
            MongoClient mongoClient = new MongoClient(connectionString);
            try {
                //   mongoClient.getAddress();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mongoClient.close();
            }
        } else {
            String driver = null;
            if (dbURI.contains("oracle")) {
                driver = "oracle.jdbc.OracleDriver";
            } else if (dbURI.contains("postgresql")) {
                driver = "org.postgresql.Driver";
            } else {
                throw new RuntimeException("Not supported DB. " + dbURI);
            }
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                Connection con = DriverManager.getConnection(dbURI);
                con.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*  public ArrayList<Monitor> getList() {
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          System.setProperty("https.protocols", "TLSv1.2");


          //ArrayList<Monitor> entities = saveOrUpdate.getUrlEntityFromDisk("main/resources/allUrls.txt");
          ArrayList<Monitor> entities = getUrlEntityFromDiskClass("allUrls.txt");

          System.out.println("URL file is read and total urls are " + entities.size());


          for (Monitor entity : entities) {
              boolean isHealthy = false;
              try {
                  if (entity.getLink().startsWith("http")) {
                      isHealthy = isHealthy(entity.getLink());
                      entity.setStatus(isHealthy);
                      // entity.setLastRefreshed(timestamp);
                  } else {
                      // it's db connection string
                      isHealthy = dbValidator(entity.getLink());
                      entity.setStatus(isHealthy);
                      //   entity.setLastRefreshed(timestamp);
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
              if (!isHealthy) {
                  System.out.println("Amber URL:" + entity.getLink());
              }

          }

          return entities;
      }*/
    public List<Monitor> refreshAll(List<Monitor> entities) {


        for (Monitor entity : entities) {
            boolean isHealthy = false;
            String responseTime = "";
            try {
                if (entity.getLink().startsWith("http")) {
                    List<String> response = isHealthy(entity.getLink());
                    responseTime = response.get(0);
                    isHealthy = Boolean.valueOf(response.get(1));
                    entity.setStatus(isHealthy);
                    entity.setResponseTime(responseTime);
                    // entity.setLastRefreshed(timestamp);
                } else {
                    // it's db connection string
                    isHealthy = dbValidator(entity.getLink());
                    entity.setStatus(isHealthy);
                    entity.setResponseTime("DB");
                    //   entity.setLastRefreshed(timestamp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!isHealthy) {
                System.out.println("Amber URL:" + entity.getLink());
            }

        }

        return entities;
    }
}
