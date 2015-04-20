using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Data.SqlClient;
using System.Configuration;

namespace WcfService4
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    public class Service1 : IService1
    {
        public string GetData(int value)
        {
            return string.Format("You entered: {0}", value);
        }

        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "userLoginDetails/{username}")]
        public userLogin userLoginDetails(String username)
        {
            userLogin user = new userLogin();
            try
            { //establishing connection 
                string str;
                string pwd;

                SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["dbString"].ConnectionString);
                conn.Open();
                SqlCommand cmd = new SqlCommand("Select * From LOGIN where USERNAME= '" + username + "'", conn);
                SqlDataReader reader = cmd.ExecuteReader();
                //String userName = ""; String pwd = ""; 
                while (reader.Read())
                {
                    user.username = reader.GetString(0);
                    user.password = reader.GetString(1);
                    //str = userName; 
                } reader.Close();
                //close the connection 

                conn.Close();
            }
            catch (Exception e)
            {
                string str;
                str = "Got exception" + e;
            }
            return user;
        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "insertRegistrationDetails/{userdetails}")]
        public String insertRegistrationDetails(String userdetails)
        {

            String str = "Inserted data";
            stringmessage message = new stringmessage();
            String cs = System.Configuration.ConfigurationManager.ConnectionStrings["dbString"].ConnectionString;
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(cs);
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand();
            //SqlDataReader rdr = null;
            
            try
            {
                con.Open();
                
                cmd.CommandType = System.Data.CommandType.Text;
                cmd.CommandText = "INSERT REGISTRATION (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,EMAILADDRESS,PHONENUMBER,DATEOFBIRTH,ADDRESS,ZIPCODE) VALUES (" + userdetails + ")";
               // cmd.CommandText = "INSERT LOGIN (USERNAME,PASSWORD) VALUES (" + logindetails + ")";

                
                /*cmd.CommandText = "INSERT UserDetails (email,password,firstName,lastName,phno,address,city,state,zipCode) VALUES ('" + userDetails.getEmail() + "'," +
                                                                                                                     userDetails.getZipCode() + ")"; */
                cmd.Connection = con;


                cmd.ExecuteNonQuery();
               
                con.Close();
                

            }
            catch (Exception e)
            {

                str = str + "Exception in insert" + e;
            }

           
            return str;
        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "insertLoginDetails/{logindetails}")]
        public String insertLoginDetails(String logindetails)
        {

            String str = "Inserted data";
            stringmessage message = new stringmessage();
            String cs = System.Configuration.ConfigurationManager.ConnectionStrings["dbString"].ConnectionString;
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(cs);
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand();
            //SqlDataReader rdr = null;
            
            try
            {
                con.Open();
                
                cmd.CommandType = System.Data.CommandType.Text;
                cmd.CommandText = "INSERT LOGIN (USERNAME,PASSWORD) VALUES (" + logindetails + ")";
                // cmd.CommandText = "INSERT LOGIN (USERNAME,PASSWORD) VALUES (" + logindetails + ")";

               
                /*cmd.CommandText = "INSERT UserDetails (email,password,firstName,lastName,phno,address,city,state,zipCode) VALUES ('" + userDetails.getEmail() + "'," +
                                                                                                                     userDetails.getZipCode() + ")"; */
                cmd.Connection = con;


                cmd.ExecuteNonQuery();
                
                con.Close();
                

            }
            catch (Exception e)
            {

                str = str + "Exception in insert" + e;
            }

            
            return str;
        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "insertItemDetails/{itemDetails}")]
        public String insertItemDetails(String itemDetails)
        {
            String str = "";
            //String insertJobDetails = items;
           // insertJobDetails = insertJobDetails + ",sysdatetime(),sysdatetime(),'P'";
            stringmessage message = new stringmessage();
            string cs = System.Configuration.ConfigurationManager.ConnectionStrings["dbString"].ConnectionString;
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(cs);
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand();
            //SqlDataReader rdr = null;

            try
            {
                con.Open();

                cmd.CommandType = System.Data.CommandType.Text;
                cmd.CommandText = "INSERT ITEMS (ITEMNAME,ITEMCATEGORY,QUANTITY,DATECREATED,CREATEDBY,YEARSOFUSAGE) VALUES (" + itemDetails + ")";
                //  cmd.CommandText = "INSERT JobDetails   (ShortDescription,LongDescription,SkillSet,JobAddress,JobCity,JobZipCode,JobPay,JobDate,Category,SubCategory,CreatedBy,CreationDate,) VALUES ('testshort','testlong','testskill','test','test',64111,35,'3/10/2015','test','test','test',sysdatetime())";           
                cmd.Connection = con;

                cmd.ExecuteNonQuery();

                con.Close();
                str = str + "Inserted data";

            }
            catch (Exception e)
            {

                str = str + "Exception in insert" + e;
            }

            message.msg = str;


            return str;

        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "updateItemDetails/{itemDetails}")]
        public String updateItemDetails(String itemDetails)
        {
            String str = "";
            String update_stmt = "";
            //String updateJobDetailsStmt = updateJobDetails;
            String[] newvals = new String[9];
            string[] words = itemDetails.Split(',');
            for (int i = 0; i < words.Length; i++)
            {
                newvals[i] = words[i];
            }

            update_stmt = "Update ITEMS set ITEMNAME=" + newvals[1] + "," + 
                "ITEMCATEGORY=" + newvals[2] + ",QUANTITY=" + newvals[3] + ",YEARSOFUSAGE=" + newvals[6] + " where ITEMID = " + newvals[0];

            str = str + "Updated data_statement" + update_stmt;


            string cs = System.Configuration.ConfigurationManager.ConnectionStrings["dbString"].ConnectionString;
            System.Data.SqlClient.SqlConnection con = new System.Data.SqlClient.SqlConnection(cs);
            System.Data.SqlClient.SqlCommand cmd = new System.Data.SqlClient.SqlCommand();
            //SqlTransaction transaction;
            // transaction = con.BeginTransaction("SampleTransaction");
            //SqlDataReader rdr = null;

            try
            {
                con.Open();

                cmd.CommandType = System.Data.CommandType.Text;
                cmd.CommandText = update_stmt;
                
                cmd.Connection = con;
                //cmd.Transaction = transaction;


                cmd.ExecuteNonQuery();
                // Commit the details
                //  transaction.Commit();

                con.Close();

                str = str + "Updated data";

            }
            catch (Exception e)
            {

                str = str + "Exception in update" + e;
            }

            


            return str;

        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "extractItemDetails/{createduser}")]
        public List<itemDetail> extractItemDetails(String createduser)
        {
            String str = "";

           // itemDetail detail = new itemDetail();
            var itemlist = new List<itemDetail>();
           
            //SqlDataReader rdr = null;

            try
            {
                

                SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["dbString"].ConnectionString);
                conn.Open();
                SqlCommand cmd1 = new SqlCommand("Select * From ITEMS where CREATEDBY = '" + createduser + "'", conn);
                SqlDataReader reader = cmd1.ExecuteReader();
                //String userName = ""; String pwd = ""; 
                while (reader.Read())
                {
                    var detail = new itemDetail();
                    detail.itemname = reader.GetString(1);
                    detail.itemcategory = reader.GetString(2);
                    detail.quantity = reader.GetDecimal(3);
                    detail.yearsused = reader.GetDecimal(6);
                    detail.itemid = reader.GetDecimal(0);
                    itemlist.Add(detail);
                    

                    //str = userName; 
                } reader.Close();
                //close the connection 

                conn.Close();

            }
            catch (Exception e)
            {

                str = str + "Exception in insert" + e;
            }




            return itemlist;

        }
        [WebInvoke(Method = "GET", ResponseFormat = WebMessageFormat.Json, UriTemplate = "retrieveAllItemDetails/")]
        public List<itemDetail> retrieveAllItemDetails()
        {
            String str = "";

            // itemDetail detail = new itemDetail();
            var itemlist = new List<itemDetail>();

            //SqlDataReader rdr = null;

            try
            {


                SqlConnection conn = new SqlConnection(ConfigurationManager.ConnectionStrings["dbString"].ConnectionString);
                conn.Open();
                SqlCommand cmd1 = new SqlCommand("Select * From ITEMS", conn);
                SqlDataReader reader = cmd1.ExecuteReader();
                //String userName = ""; String pwd = ""; 
                while (reader.Read())
                {
                    var detail = new itemDetail();
                    detail.itemname = reader.GetString(1);
                    detail.itemcategory = reader.GetString(2);
                    detail.quantity = reader.GetDecimal(3);
                    detail.yearsused = reader.GetDecimal(6);
                    detail.itemid = reader.GetDecimal(0);
                    detail.createdby = reader.GetString(5);
                    itemlist.Add(detail);


                    //str = userName; 
                } reader.Close();
                //close the connection 

                conn.Close();

            }
            catch (Exception e)
            {

                str = str + "Exception in insert" + e;
            }




            return itemlist;

        }
       

        public CompositeType GetDataUsingDataContract(CompositeType composite)
        {
            if (composite == null)
            {
                throw new ArgumentNullException("composite");
            }
            if (composite.BoolValue)
            {
                composite.StringValue += "Suffix";
            }
            return composite;
        }
    }




        public class userLogin
        {
            public string username { get; set; }
            public string password { get; set; }
        }


       
 public class itemDetail
    {

        public Decimal itemid { get; set; }
        public Decimal yearsused { get; set; }
        public String itemcategory { get; set; }
        public Decimal  quantity { get; set; }
        public String itemname { get; set; }
        public String createdby { get; set; }
        

    }
    public class userDetails {
        public string firstname { get; set; }
        public string lastname { get; set; }
        public string username { get; set; }
        public string password { get; set; }
        public string emailaddress { get; set; }
        public decimal mobilenumber { get; set; }
        public string address { get; set; }
        public DateTime dateofbirth { get; set; }
        public decimal zipcode { get; set; }
    
    }
    public class stringmessage {
        public string msg { get; set; }
    }


    }


