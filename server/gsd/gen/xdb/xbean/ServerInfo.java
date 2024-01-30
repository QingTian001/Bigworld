package xbean;

@SuppressWarnings("unchecked")
public final class ServerInfo extends  pcore.db.Bean  {
       public static ServerInfo newBean() { return new ServerInfo(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected long startTimeMills;

       public ServerInfo() {
       }

       public ServerInfo(long startTimeMills ) {
           this.startTimeMills = startTimeMills; 
       }

       @Override
       public ServerInfo copy() {
           return new ServerInfo(getStartTimeMills());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("startTimeMills").append(':').append(getStartTimeMills()).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_startTimeMills extends pcore.db.FieldLog<ServerInfo> {
            _Log_startTimeMills(ServerInfo _this, long value) {
                super(_this);
                this.value = value;
            }

            long value;

            @Override
            public void commit() {
                this.bean.startTimeMills = this.value;
            }
       }

       public long getStartTimeMills() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return startTimeMills;
            _Log_startTimeMills log = txn.getField(objectId + 2);
            return log == null ? startTimeMills : log.value;
       }

       public void setStartTimeMills(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_startTimeMills log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_startTimeMills(this, _value_));
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
           os.writeCompactUint(pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(startTimeMills);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)): startTimeMills = os.readLong(); break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
       }
}
