package org.apache.jsp.AppServer;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.google.gson.Gson;
import DB.Files;
import java.util.List;
import java.util.ArrayList;
import DB.DbConnection;
import java.util.Vector;
import java.util.Iterator;

public final class controller_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


    String key = request.getParameter("key");
    DbConnection con = new DbConnection();
    System.out.println("key " + key);

    if (key.equals("register")) {

        String name = request.getParameter("name");
        String phone = request.getParameter("mobile");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println(name + " " + email + " " + phone + " " + password);
        String str = "INSERT INTO `tbl_registration`(`name`,`phone`,`email`,`password`) "
                + "VALUES('" + name + "','" + phone + "','" + email + "','" + password + "')";

        if (con.putData(str) > 0) {

            out.println("success");

        } else {
            out.println("failed");
        }

    }
    if (key.equals("login")) {
        String id = "";
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        String uid = "";

        String str = "SELECT * FROM `tbl_registration` WHERE `email`='" + email + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v0 = (Vector) itr.next();
            uid = v0.get(0).toString();
            String str2 = "SELECT * FROM `tbl_registration` WHERE `email`='" + email + "' AND `password`='" + pass + "' ";
            System.out.println(str2);
            Iterator itr2 = con.getData(str2).iterator();
            if (itr2.hasNext()) {

                Vector v = (Vector) itr2.next();
                id = "" + v.get(0) + ":" + v.get(1) + ":" + v.get(2) + ":" + v.get(3) + ":" + v.get(4) + "";

                System.out.println("logresp=" + id);
                out.print(id + "&");
            } else {
                System.out.println("failedPass&" + uid);
                out.println("failedPass&" + uid);
            }
        } else {
            out.println("failed&");
        }
    }

    if (key.equals("getPhone")) {
        String phone = "";
        String message;
        String email = request.getParameter("email");
        String str = "SELECT `phone` FROM `tbl_registration` WHERE `email`='" + email + "'";
        System.out.println(str);
        Iterator itr = con.getData(str).iterator();
        if (itr.hasNext()) {
            Vector v = (Vector) itr.next();

            phone = v.get(0).toString();

            out.print(phone);
        } else {
            out.println("failed");
        }
    }

//    ForgotChangePass start
    if (key.equals("ForgotChangePass")) {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        System.out.println(email + "         " + newPassword);
        String str = "UPDATE `tbl_registration` SET `password`='" + newPassword + "' WHERE `email`='" + email + "'";

        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }
//    ForgotChangePass send

//UploadText start
    if (key.equals("UploadText")) {

        String name = request.getParameter("name");
        String text = request.getParameter("text");
        String enckey = request.getParameter("enckey");
        String uid = request.getParameter("uid");

        // String str = "INSERT INTO `text_file`(`uid`,`name`,`text`,`key`,`status`) VALUES('1','" + name + "','" + text + "','" + enckey + "','1')";
        String str = "INSERT INTO `text_file`(`uid`,`name`,`text`,`key`,`status`) VALUES('" + uid + "','" + name + "','" + text + "','" + enckey + "','1')";

        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }
//UploadText end

//getEncry_text_list start
    if (key.equals("getEncry_text_list")) {

        String uid = request.getParameter("uid");
        String text = "", keys = "", id = "", list = "";
        //  String qry = "SELECT `fid`,`name`,`text`,`key` FROM `text_file` WHERE `uid`='1' AND `status`='1'";
        String qry = "SELECT `fid`,`name`,`text`,`key` FROM `text_file` WHERE `uid`='" + uid + "' AND `status`='1'";
        System.out.println(qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                id += v.get(0) + "&";
                text += v.get(2) + "&";
                keys += v.get(3) + "&";
                list += "" + v.get(1) + "&";
            }
            out.print(id + "#" + text + "#" + keys + "#" + list);
        } else {

            out.print("failed");
        }
    }
//getEncry_text_list end

//Decrypt_text_Update_Action start
    if (key.equals("Decrypt_text_Update_Action")) {

        String id = request.getParameter("id");
        String dec_text = request.getParameter("dec_text");
        String str = "UPDATE `text_file` SET `text`='" + dec_text + "',`status`='0' WHERE `fid`='" + id + "'";
        System.out.println(str);
        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }
//Decrypt_text_Update_Action end    

//getDecry_text_list start
    if (key.equals("getDecry_text_list")) {

        String uid = request.getParameter("uid");
        String text = "", keys = "", id = "", list = "";
        String qry = "SELECT `fid`,`name`,`text`,`key` FROM `text_file` WHERE `uid`='" + uid + "' AND `status`='0'";
        System.out.println(qry);
        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                id += v.get(0) + "&";
                text += v.get(2) + "&";
                keys += v.get(3) + "&";
                list += "" + v.get(1) + "&";
            }
            out.print(id + "#" + text + "#" + keys + "#" + list);
        } else {

            out.print("failed");
        }
    }
//getDecry_text_list end

//UploadImage start
    if (key.equals("UploadImage")) {

        String name = request.getParameter("name");
        String enc_imag = request.getParameter("enc_imag");
        String enckey = request.getParameter("enckey");
        String uid = request.getParameter("uid");
        String str = "INSERT INTO `image_file`(`uid`,`name`,`image`,`key`,`status`) VALUES('" + uid + "','" + name + "','" + enc_imag + "','" + enckey + "','1')";

        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }
//UploadImage end

//getEncreypted_images start
    if (key.equals("getEncreypted_images")) {
        String result = "";
        String uid = request.getParameter("uid");
        String str = "SELECT `fid`,`name`,`image`,`key`,status FROM `image_file` WHERE `uid`='" + uid + "' AND `status`='1'";
        List<Files> allInfos = new ArrayList<Files>();

        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            Files bean = new Files();
            bean.setFid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setKey(vv.get(3).toString());
            bean.setImage(vv.get(2).toString());
            bean.setStatus(vv.get(4).toString());

            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }
//getEncreypted_images send

//getDecreypted_images start
    if (key.equals("getDecreypted_images")) {
        String result = "";

        List<Files> allInfos = new ArrayList<Files>();
        String uid = request.getParameter("uid");
        String str = "SELECT `fid`,`name`,`image`,`key`,status FROM `image_file` WHERE `uid`='" + uid + "' AND `status`='0'";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();
            Files bean = new Files();
            bean.setFid(vv.get(0).toString());
            bean.setName(vv.get(1).toString());
            bean.setKey(vv.get(3).toString());
            bean.setImage(vv.get(2).toString());
            bean.setStatus(vv.get(4).toString());

            allInfos.add(bean);
        }
        Gson gson = new Gson();

        String infoall = gson.toJson(allInfos);
        if (infoall.equals("[]")) {
            out.println("failed");
        } else {
            out.println(infoall);
        }
        System.out.println(infoall);
    }
//getDeceypted_images send

//Decrypt_image_Update_Action start
    if (key.equals("Decrypt_image_Update_Action")) {

        String id = request.getParameter("id");
        String dec_text = request.getParameter("dec_text");
        String str = "UPDATE `image_file` SET `image`='" + dec_text + "',`status`='0' WHERE `fid`='" + id + "'";
        System.out.println(str);
        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }
//Decrypt_image_Update_Action end 

//ChangePassword start
    if (key.equals("ChangePassword")) {

        String rid = request.getParameter("uid");
        String newPassword = request.getParameter("newPassword");
        String str = "UPDATE `tbl_registration` SET `password`='" + newPassword + "' WHERE `rid`='" + rid + "'";
        System.out.println(str);
        if (con.putData(str) > 0) {
            out.println("success");

        } else {
            out.println("failed");
        }

    }

//ChangePassword end
//uploadBackup start
    if (key.equals("uploadBackup")) {

        int count = 0;
        String uid = request.getParameter("uid");
        String qry = "SELECT * FROM `text_file` WHERE `status`='1' AND `uid`='" + uid + "'";
        String qry1 = "SELECT * FROM `image_file` WHERE `status`='1' AND `uid`='" + uid + "'";
        System.out.println(qry + "\n" + qry1);

        Iterator it = con.getData(qry).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                String str = "SELECT * FROM `backup` WHERE `type`='text' AND `uid`='" + uid + "' AND `name`='" + v.get(2) + "' AND `file`='" + v.get(3) + "'";
                System.out.println("str=" + str);

                String name = v.get(2).toString();
                String file = v.get(3).toString();
                String keey = v.get(4).toString();
                Iterator itr = con.getData(str).iterator();
                if (itr.hasNext()) {
                    Vector v2 = (Vector) itr.next();
                    System.out.println("no val" + v2.toString());
                } else {
                    String str2 = "INSERT INTO `backup`(`uid`,`name`,`file`,`type`,`key`) VALUES ('" + uid + "','" + name + "','" + file + "','text','" + keey + "')";
                    System.out.println("str2=" + str2);
                    if (con.putData(str2) > 0) {
                        count++;
                    }
                }

            }

        }

//            .................
        Iterator it2 = con.getData(qry1).iterator();
        if (it2.hasNext()) {
            System.out.println("in image");
            while (it2.hasNext()) {
                Vector v = (Vector) it2.next();
                String str = "SELECT * FROM `backup` WHERE `type`='image' AND `uid`='" + uid + "' AND `name`='" + v.get(2) + "' AND `file`='" + v.get(3) + "'";
                System.out.println("str=" + str);
                Iterator itr = con.getData(str).iterator();
                if (itr.hasNext()) {

                } else {
                    String str2 = "INSERT INTO `backup`(`uid`,`name`,`file`,`type`,`key`) VALUES ('" + uid + "','" + v.get(2) + "','" + v.get(3) + "','image','" + v.get(4) + "')";
                    System.out.println("str2=" + str2);
                    if (con.putData(str2) > 0) {
                        count++;
                    }
                }

            }

        }

        System.out.println("count=" + count);
        out.print(count);

    }

//uploadBackup end
//importBackup start
    if (key.equals("importBackup")) {

        int count = 0;
        String uid = request.getParameter("uid");
        String asd = "SELECT * FROM `backup` ";
        Iterator it = con.getData(asd).iterator();
        if (it.hasNext()) {
            while (it.hasNext()) {
                Vector v = (Vector) it.next();
                String name = v.get(2).toString();
                String file = v.get(3).toString();
                String keey = v.get(5).toString();
                if (v.get(4).equals("text")) {

                    String str = "SELECT * FROM `text_file` WHERE `status`='1' AND `uid`='" + uid + "' AND `name`='" + v.get(2) + "' AND `text`='" + v.get(3) + "' ";
                    Iterator itr = con.getData(str).iterator();
                    if (itr.hasNext()) {
                        Vector v2 = (Vector) itr.next();
                        System.out.println("yes val" + v2.toString());
                    } else {

                        String str2 = " INSERT INTO `text_file`(`uid`,`name`,`text`,`key`,`status`) VALUES('" + uid + "','" + name + "','" + file + "','" + keey + "','1')";
                        System.out.println("str2=" + str2);
                        if (con.putData(str2) > 0) {
                            count++;
                        }

                    }

                } else {
                    String str = "SELECT * FROM `image_file` WHERE `status`='1' AND `uid`='" + uid + "' AND `name`='" + v.get(2) + "' AND `image`='" + v.get(3) + "' ";
                    Iterator itr = con.getData(str).iterator();
                    if (itr.hasNext()) {
                        Vector v2 = (Vector) itr.next();
                        System.out.println("yes val" + v2.toString());
                    } else {

                        String str2 = " INSERT INTO `image_file`(`uid`,`name`,`image`,`key`,`status`) VALUES('" + uid + "','" + name + "','" + file + "','" + keey + "','1')";
                        System.out.println("str2=" + str2);
                        if (con.putData(str2) > 0) {
                            count++;
                        }

                    }

                }

            }
        }

        System.out.println("count=" + count);
        out.print(count);

    }

//importBackup end 
// getDecFileForDec satr
    if (key.equals("getDecFileForDec")) {
        String uid = request.getParameter("userId");
        String tid = "", tpass = "", tfile = "", iid = "", ipass = "", ifile = "", textdata = "", imagedata = "", finaldata = "no";

          String temptext="no",tempimage="no";
        
        //getting text files
        String str = "SELECT `fid`,`text`,`key`,status FROM `text_file` where `uid`='" + uid + "' AND `status`='0'";
        System.out.println(str);
        Vector v = con.getData(str);
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            Vector vv = (Vector) itr.next();

            temptext="yes";
            
            tid += vv.get(0) + "#";
            tfile += vv.get(1) + "#";
            tpass += vv.get(2) + "#";
        }
        
        textdata = tid + "&" + tfile + "&" + tpass;
        
        
        System.out.println("\n\n textdata=   " + textdata);

        //getting text files
        String str2 = "SELECT `fid`,`image`,`key`,status FROM `image_file` where `uid`='" + uid + "'  AND `status`='0'";
        System.out.println(str2);
        Vector v2 = con.getData(str2);
        Iterator itr2 = v2.iterator();
        while (itr2.hasNext()) {
            Vector vv2 = (Vector) itr2.next();

            tempimage="yes";
            
            iid += vv2.get(0) + "#";
            ifile += vv2.get(1) + "#";
            ipass += vv2.get(2) + "#";
        }
        imagedata = iid + "&" + ifile + "&" + ipass;

        System.out.println("\n\n imagedata=   " + imagedata);

        
        if(textdata.equals("&&")){
            textdata="no";
        }
         if(imagedata.equals("&&")){
            imagedata="no";
        }
      finaldata = textdata + "%%" + imagedata;
        System.out.println("finaldata=   "+finaldata);
//        if (finaldata.equals("no")) {
//            out.println("failed");
//        } else {
//            out.print(finaldata);
//        }
        
         out.print(finaldata);

    }

//getDecFileForDec end 
//UpdatenewEncrytedfiles start
    if (key.equals("UpdatenewEncrytedfiles")) {

        String finaldata[] = request.getParameter("finaldata").trim().split("%%");
        System.out.println("finaldata\n...........\n\n\n " + request.getParameter("finaldata").trim());
        String tid[], tfile[], iid[], ifile[], textdata = "", imagedata = "", resp="no";
       
      
        
        textdata = finaldata[0];
        imagedata = finaldata[1];

         if(!textdata.equals("no")){
        
        String txtdata[] = textdata.trim().split("&");
        tid = txtdata[0].trim().split("#");
        tfile = txtdata[1].trim().split("#");
        
        System.out.println("tid.length="+tid.length);
        
        //updatae enctext data
        for (int i = 0; i < tid.length; i++) {

            String text = tfile[i];
            String id = tid[i];
            String str = " UPDATE `text_file` SET `text`='" + text + "' , `status`='1' WHERE `fid`='" + id + "' ";
            System.out.println(i+" str2=" + str);
            if (con.putData(str) > 0) {
                System.out.println("\n updatae enctext=");
                resp = "yes";
            }

        }
        
         }
         
          if(!imagedata.equals("no")){
              
         
        String imgdata[] = imagedata.trim().split("&");
        iid = imgdata[0].trim().split("#");
        ifile = imgdata[1].trim().split("#");

        
        //updatae imgtext data
        for (int i = 0; i < iid.length; i++) {
            String text2 = ifile[i];
            String id2 = iid[i];
            String str2 = " UPDATE `image_file` SET `image`='" + text2 + "' , `status`='1' WHERE `fid`='" + id2 + "' ";
            System.out.println("\n\nstr2=" + str2);
            if (con.putData(str2) > 0) {
                resp = "yes";
                 System.out.println("\n updatae imgtext=");
            }
        }
        
         }
        if(resp.equals("no")){
            out.println("failed");
        }else{
              out.println("success");
        }

    }
// UpdatenewEncrytedfiles end 

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
