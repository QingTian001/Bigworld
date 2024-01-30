package xbean;

@SuppressWarnings("unchecked")
public final class User extends  pcore.db.Bean  {
       public static User newBean() { return new User(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected long roleId;

       public User() {
       }

       public User(long roleId ) {
           this.roleId = roleId; 
       }

       @Override
       public User copy() {
           return new User(getRoleId());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("roleId").append(':').append(getRoleId()).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_roleId extends pcore.db.FieldLog<User> {
            _Log_roleId(User _this, long value) {
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
            _Log_roleId log = txn.getField(objectId + 2);
            return log == null ? roleId : log.value;
       }

       public void setRoleId(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_roleId log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_roleId(this, _value_));
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
           os.writeSize(1);
           os.writeCompactUint(pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(roleId);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)): roleId = os.readLong(); break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
       }
}
