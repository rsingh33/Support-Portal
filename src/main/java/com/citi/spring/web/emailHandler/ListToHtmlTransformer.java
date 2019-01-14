package com.citi.spring.web.emailHandler;


import com.citi.spring.web.dao.entity.Handover;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * +
 * Transforms the message into email format for handover
 */
public class ListToHtmlTransformer {
  static   DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public static String compose(java.util.Collection<Handover> handover) {
        StringBuilder email = new StringBuilder();
        email.append("<html>");
        email.append("<head>\n");
        email.append("<meta charset='utf-8'>\n");
        email.append("<title>Daily Email Handover</title>\n");
        email.append("<style>\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "}\n" +
                "</style>");
        email.append("</head>\n\n");
        email.append("<body>");
        email.append("<table style='border:2px solid black;width:100%'>");
        email.append("<tr ><td bgcolor=\"#A8CBFD\" align=\"center\" colspan=\"8\" style='font-weight:bold;font-size:25px'>Open Issues</td></tr>");
        email.append("<tr bgcolor=\"#dfe8f0\">");
        email.append("<th>Reporter</th>");
        email.append("<th>Email Subject</th>");
        email.append("<th>Tracking</th>");
        email.append("<th>Comments</th>");
        email.append("<th>Last Modified</th>");
        email.append("<th>Status</th>");
        email.append("<th>Environment</th>");
        email.append("<th>Currently With</th>");
        email.append("</tr>");


        for (Handover row : handover) {

            email.append("<tr bgcolor=\"#f1f9fc\">");

            email.append("<td>");
            email.append(row.getReportedBy());
            email.append("</td>");

            email.append("<td>");
            email.append(row.getEmailSubject());
            email.append("</td>");

            email.append("<td>");
            email.append(row.getTracking());
            email.append("</td>");

            email.append("<td>");
            email.append(row.getComments());
            email.append("</td>");

            email.append("<td>");
            email.append("Time: " + sdf.format(row.getLastMod()) + ", User:" + row.getLastModUser());
            email.append("</td>");


            email.append("<td>");
            email.append(row.getStatus());
            email.append("</td>");


            email.append("<td>");
            email.append(row.getEnvironment());
            email.append("</td>");


            email.append("<td>");
            email.append(row.getCurrentlyWith());
            email.append("</td>");
            email.append("</tr>");
        }

        email.append("</table></body></html>");
        return email.toString();

    }

}