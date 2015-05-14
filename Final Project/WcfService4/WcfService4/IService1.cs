using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WcfService4
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        string GetData(int value);

        [OperationContract]
        userLogin userLoginDetails(String username);

        [OperationContract]
        String insertRegistrationDetails(String userdetails);
        [OperationContract]
        String insertLoginDetails(String logindetails);
        [OperationContract]
        String updateItemApprovalstatus(String approvaldetails);

        [OperationContract]
         String insertItemDetails(String itemDetails);
        [OperationContract]
        String updateItemDetails(String itemDetails);
        [OperationContract]
        String updateItembyId(String subscribedetails);
        [OperationContract]
        List<itemDetail> retrieveItemsbySubsciption(String user);

        [OperationContract]
        List<itemDetail> extractItemDetails(String createduser);
        [OperationContract]
        userDetails retrieveUserDetails(String username);
        [OperationContract]
        List<itemDetail> retrieveAllItemDetails();
        [OperationContract]
        List<itemDetail> retrieveItemsbyCategory(String category);




        [OperationContract]
        CompositeType GetDataUsingDataContract(CompositeType composite);

        // TODO: Add your service operations here
    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }
}
