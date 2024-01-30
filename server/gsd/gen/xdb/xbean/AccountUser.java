package xbean;

@SuppressWarnings("unchecked")
public final class AccountUser extends  pcore.db.Bean  {
       public static AccountUser newBean() { return new AccountUser(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected String accountId;
       protected long userId;
       protected long roleId;

       public AccountUser() {
            this.accountId = "";


       }

       public AccountUser(String accountId , long userId , long roleId ) {
           this.accountId = accountId; 
           this.userId = userId; 
           this.roleId = roleId; 
       }

       @Override
       public AccountUser copy() {
           return new AccountUser(getAccountId(), getUserId(), getRoleId());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("accountId").append(':').append(getAccountId()).append(',');
           sb.append("userId").append(':').append(getUserId()).append(',');
           sb.append("roleId").append(':').append(getRoleId()).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_accountId extends pcore.db.FieldLog<AccountUser> {
            _Log_accountId(AccountUser _this, String value) {
                super(_this);
                this.value = value;
            }

            String value;

            @Override
            public void commit() {
                this.bean.accountId = this.value;
            }
       }

       public String getAccountId() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return accountId;
            _Log_accountId log = txn.getField(objectId + 2);
            return log == null ? accountId : log.value;
       }

       public void setAccountId(String _value_) {
            if (_value_ == null) throw new NullPointerException();
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_accountId log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_accountId(this, _value_));
            else log.value = _value_;
       }

       private final static class _Log_userId extends pcore.db.FieldLog<AccountUser> {
            _Log_userId(AccountUser _this, long value) {
                super(_this);
                this.value = value;
            }

            long value;

            @Override
            public void commit() {
                this.bean.userId = this.value;
            }
       }

       public long getUserId() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return userId;
            _Log_userId log = txn.getField(objectId + 3);
            return log == null ? userId : log.value;
       }

       public void setUserId(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_userId log = txn.getFieldAtCurrentSavePoint(objectId + 3);
            if (log == null) txn.putField(objectId + 3, new _Log_userId(this, _value_));
            else log.value = _value_;
       }

       private final static class _Log_roleId extends pcore.db.FieldLog<AccountUser> {
            _Log_roleId(AccountUser _this, long value) {
                super(_this);
                this.value = value;
            }

            long value;

            @Override
            public void commit() {
                this.bean.roleId = this.value;
            }
       }

       public long getRoleId() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return roleId;
            _Log_roleId log = txn.getField(objectId + 4);
            return log == null ? roleId : log.value;
       }

       public void setRoleId(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_roleId log = txn.getFieldAtCurrentSavePoint(objectId + 4);
            if (log == null) txn.putField(objectId + 4, new _Log_roleId(this, _value_));
            else log.value = _value_;
       }



       @Override
       protected void setChildrenRootInTxn(pcore.db.Transaction txn, long root) {
       }

       @Override
       protected void applyChildrenRootInTxn(long root) {
       }

       @Override
       public void marshal(pcore.marshal.Octets os) {
           marshalCompatible(os);
       }

       @Override
       public void unmarshal(pcore.marshal.Octets os) {
           unmarshalCompatible(os);
       }

       @Override
       public void marshalCompatible(pcore.marshal.Octets os) {
           os.writeSize(3);
           os.writeCompactUint(pcore.marshal.Tag.STRING | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeString(accountId);
           os.writeCompactUint(pcore.marshal.Tag.LONG | (2 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(userId);
           os.writeCompactUint(pcore.marshal.Tag.LONG | (3 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(roleId);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.STRING | (1 << pcore.marshal.Tag.TAG_SHIFT)): accountId = os.readString(); break;
                   case ( pcore.marshal.Tag.LONG | (2 << pcore.marshal.Tag.TAG_SHIFT)): userId = os.readLong(); break;
                   case ( pcore.marshal.Tag.LONG | (3 << pcore.marshal.Tag.TAG_SHIFT)): roleId = os.readLong(); break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
       }
}
