using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using System.Net;
using System.IO;
namespace TestWS
{
    public class Service
    {
        [Test]
        public void UserValidation()
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8871/Service1.svc/userLoginDetails/shashi@gmail");
            req.Method ="GET";
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;
            if (resp.StatusCode == HttpStatusCode.OK)
            {
                using (Stream respStream = resp.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(respStream, Encoding.UTF8);
                    Assert.AreEqual("{\"password\":\"reddy\",\"username\":\"shashi@gmail\"}", reader.ReadToEnd().ToString());
                }

            }


        }
        [Test]
        public void ExtractItemDetails()
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8871/Service1.svc/extractItemDetails/shashi@gmail");
            req.Method = "GET";
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;
            if (resp.StatusCode == HttpStatusCode.OK)
            {
                using (Stream respStream = resp.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(respStream, Encoding.UTF8);
                    Assert.AreEqual("[{\"createdby\":null,\"itemcategory\":\"Furniture\",\"itemid\":2000,\"itemname\":\"table\",\"quantity\":2,\"yearsused\":3},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2001,\"itemname\":\"chair\",\"quantity\":2,\"yearsused\":2},{\"createdby\":null,\"itemcategory\":\"Electronics\",\"itemid\":2002,\"itemname\":\"headset\",\"quantity\":1,\"yearsused\":4},{\"createdby\":null,\"itemcategory\":\"Sports\",\"itemid\":2003,\"itemname\":\"racket\",\"quantity\":2,\"yearsused\":1},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2010,\"itemname\":\"chair\",\"quantity\":2,\"yearsused\":4},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2011,\"itemname\":\"chair\",\"quantity\":2,\"yearsused\":4},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2006,\"itemname\":\"shoes\",\"quantity\":4,\"yearsused\":5},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2007,\"itemname\":\"headsetiphone\",\"quantity\":1,\"yearsused\":4},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2008,\"itemname\":\"toy\",\"quantity\":1,\"yearsused\":2},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2009,\"itemname\":\"suitcase\",\"quantity\":1,\"yearsused\":3},{\"createdby\":null,\"itemcategory\":\"Household\",\"itemid\":2012,\"itemname\":\"chair\",\"quantity\":2,\"yearsused\":4}]", reader.ReadToEnd().ToString());
                }

            }


        }
       

        
        [Test]
        public void InsertItemDetails()
        {
            WebRequest req = WebRequest.Create(@"http://localhost:8871/Service1.svc/insertItemDetails/'chair','Household','2','04-10-2015','shashi@gmail','4'");
            req.Method = "GET";
            HttpWebResponse resp = req.GetResponse() as HttpWebResponse;
            if (resp.StatusCode == HttpStatusCode.OK)
            {
                using (Stream respStream = resp.GetResponseStream())
                {
                    StreamReader reader = new StreamReader(respStream, Encoding.UTF8);
                    Assert.AreEqual("\"Inserted data\"", reader.ReadToEnd().ToString());
                }

            }


        }

    }
} 
        
        


    

