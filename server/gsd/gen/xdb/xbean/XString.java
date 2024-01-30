package xbean;

@SuppressWarnings("unchecked")
public final class XString extends  pcore.db.Bean  {
       public static XString newBean() { return new XString(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected String value;

       public XString() {
            this.value = "";
       }

       public XString(String value ) {
           this.value = value; 
       }

       @Override
       public XString copy() {
           return new XString(getValue());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("value").append(':').append(getValue()).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_value extends pcore.db.FieldLog<XString> {
            _Log_value(XString _this, String value) {
                super(_this);
                this.value = value;
            }

            String value;

            @Override
            public void commit() {
                this.bean.value = this.value;
            }
       }

       public String getValue() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return value;
            _Log_value log = txn.getField(objectId + 2);
            return log == null ? value : log.value;
       }

       public void setValue(String _value_) {
            if (_value_ == null) throw new NullPointerException();
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_value log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_value(this, _value_));
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
           os.writeCompactUint(pcore.marshal.Tag.STRING | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeString(value);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.STRING | (1 << pcore.marshal.Tag.TAG_SHIFT)): value = os.readString(); break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
       }
}
