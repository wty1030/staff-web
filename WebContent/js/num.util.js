//****************************************************************
//* �������ƣ�DataLength
//* ��    �ܣ��������ݵĳ���
//* ��ڲ�����fData����Ҫ���������
//* ���ڲ���������fData�ĳ���(Unicode����Ϊ2����Unicode����Ϊ1)
//*****************************************************************
function DataLength(fData)
{
    var intLength=0
    for (var i=0;i<fData.length;i++)
    {
        if ((fData.charCodeAt(i) < 0) || (fData.charCodeAt(i) > 255))
            intLength=intLength+2
        else
            intLength=intLength+1    
    }
    return intLength
}


//****************************************************************
//* �������ƣ�DataLength
//* ��    �ܣ��������ݵĳ���
//* ��ڲ�����fData����Ҫ���������
//* ���ڲ���������fData�ĳ���(Unicode����Ϊ2����Unicode����Ϊ1)
//*****************************************************************
function DataLength(fData)
{
    var intLength=0
    for (var i=0;i<fData.length;i++)
    {
        if ((fData.charCodeAt(i) < 0) || (fData.charCodeAt(i) > 255))
            intLength=intLength+2
        else
            intLength=intLength+1    
    }
    return intLength
} 

//****************************************************************
//* �������ƣ�IsEmpty
//* ��    �ܣ��ж��Ƿ�Ϊ��
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����                              
//*           False���ǿ�
//*****************************************************************
function IsEmpty(fData)
{
    return ((fData==null) || (fData.length==0) )
} 


//****************************************************************
//* �������ƣ�IsDigit
//* ��    �ܣ��ж��Ƿ�Ϊ����
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����0��9������                              
//*           False������0��9������ 
//*****************************************************************
function IsDigit(fData)
{
    return ((fData>="0") && (fData<="9"))
} 


//****************************************************************
//* �������ƣ�IsInteger
//* ��    �ܣ��ж��Ƿ�Ϊ������
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True�������������������ǿյ�                            
//*           False����������
//*****************************************************************
function IsInteger(fData)
{
    //���Ϊ�գ�����true
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf(".")!=-1) || (fData.indexOf("-")!=-1))
        return false    
    
    return true    
} 

//****************************************************************
//* �������ƣ�IsNoEmptyInteger
//* ��    �ܣ��ж��Ƿ�Ϊ������
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True�������������Ҳ�Ϊ�� ,��Ϊ��              
//*           False����������,�����ǿ�,��Ϊ��
//*****************************************************************
function IsNoEmptyInteger(fData)
{
    //���Ϊ�գ�����true
	if(fData==0)
		return false;
    if (IsEmpty(fData))
        return false;
    if ((isNaN(fData)) || (fData.indexOf(".")!=-1) || (fData.indexOf("-")!=-1))
        return false    
    
    return true    
} 

//****************************************************************
//* �������ƣ�IsEmail
//* ��    �ܣ��ж��Ƿ�Ϊ��ȷ��Email��ַ
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����ȷ��Email��ַ�����߿�                              
//*           False�������Email��ַ
//*****************************************************************
function IsEmail(fData)
{
    if (IsEmpty(fData))
        return true
    if (fData.indexOf("@")==-1)
        return false
    var NameList=fData.split("@");
    if (NameList.length!=2)
        return false  
    if (NameList[0].length<1 )
        return false   
    if (NameList[1].indexOf(".")<=0)
        return false 
    if (fData.indexOf("@")>fData.indexOf(".")) 
 return false
    if (fData.indexOf(".")==fData.length-1)
 return false
    
    return true    
} 

//****************************************************************
//* �������ƣ�IsPhone
//* ��    �ܣ��ж��Ƿ�Ϊ��ȷ�ĵ绰���루���Ժ�"()"��"����"��"+"��"-"�Ϳո�
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����ȷ�ĵ绰���룬���߿�                              
//*           False������ĵ绰����
//* ������Ϣ��
//*****************************************************************
function IsPhone(fData)
{
    var str;
    var fDatastr="";
    if (IsEmpty(fData))
        return true
    for (var i=0;i<fData.length;i++)
    {
        str=fData.substring(i,i+1);
        if (str!="(" && str!=")" && str!="��" && str!="��" && str!="+" && str!="-" && str!=" ")
           fDatastr=fDatastr+str;
    }  
    //alert(fDatastr);  
    if (isNaN(fDatastr))
        return false 
    return true    
} 

//****************************************************************
//* �������ƣ�IsPlusNumeric
//* ��    �ܣ��ж��Ƿ�Ϊ��ȷ�����������Ժ�С�����֣�
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����ȷ�����������߿�                              
//*           False�����������
//* ������Ϣ��
//*****************************************************************
function IsPlusNumeric(fData)
{
    if (IsEmpty(fData))
        return true
    if ((isNaN(fData)) || (fData.indexOf("-")!=-1))
        return false 
    return true    
} 




//****************************************************************
//* �������ƣ�IsNumeric
//* ��    �ܣ��ж��Ƿ�Ϊ��ȷ�����֣�����Ϊ������С����
//* ��ڲ�����fData��Ҫ��������
//* ���ڲ�����True����ȷ�����֣����߿�                              
//*           False�����������
//* ������Ϣ��
//*****************************************************************
function IsNumeric(fData)
{
    if (IsEmpty(fData))
        return true
    if (isNaN(fData))
        return false
        
    return true    
} 


//****************************************************************
//* �������ƣ�IsIntegerInRange
//* ��    �ܣ��ж�һ�������Ƿ���ָ���ķ�Χ��
//* ��ڲ�����fInput��Ҫ��������
//*           fLower�����ķ�Χ���ޣ����û�����ޣ�����null
//*           fHigh���������ޣ����û�����ޣ�����null
//* ���ڲ�����True����ָ���ķ�Χ��                              
//*           False������ָ����Χ
//*****************************************************************
function IsIntegerInRange(fInput,fLower,fHigh)
{
    if (fLower==null)
        return (fInput<=fHigh)
    else if (fHigh==null)
        return (fInput>=fLower) 
    else         
        return ((fInput>=fLower) && (fInput<=fHigh))
}
