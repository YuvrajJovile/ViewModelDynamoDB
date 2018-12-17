package sai.com.viewmodeljava.dbDJOs;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "dynamosample-mobilehub-244298924-usersTable")

public class UsersTableDO {
    private String _userName;
    private String _passWord;
    private String _timeStamp;


    @DynamoDBHashKey(attributeName = "userName")
    @DynamoDBAttribute(attributeName = "userName")
    public String getUserName() {
        return _userName;
    }

    public void setUserName(final String _userName) {
        this._userName = _userName;
    }
    @DynamoDBAttribute(attributeName = "passWord")
    public String getPassWord() {
        return _passWord;
    }

    public void setPassWord(final String _passWord) {
        this._passWord = _passWord;
    }

    @DynamoDBAttribute(attributeName = "timeStamp")
    public String get_timeStamp() {
        return _timeStamp;
    }

    public void set_timeStamp(String _timeStamp) {
        this._timeStamp = _timeStamp;
    }
}
