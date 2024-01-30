package xbean;

@SuppressWarnings("unchecked")
public final class TestInnerBean extends  pcore.db.Bean  {
       public static TestInnerBean newBean() { return new TestInnerBean(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected long it1;

       public TestInnerBean() {
       }

       public TestInnerBean(long it1 ) {
           this.it1 = it1; 
       }

       @Override
       public TestInnerBean copy() {
           return new TestInnerBean(getIt1());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("it1").append(':').append(getIt1()).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_it1 extends pcore.db.FieldLog<TestInnerBean> {
            _Log_it1(TestInnerBean _this, long value) {
                super(_this);
                this.value = value;
            }

            long value;

            @Override
            public void commit() {
                this.bean.it1 = this.value;
            }
       }

       public long getIt1() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return it1;
            _Log_it1 log = txn.getField(objectId + 2);
            return log == null ? it1 : log.value;
       }

       public void setIt1(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_it1 log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_it1(this, _value_));
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
           os.writeCompactUint(pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(it1);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)): it1 = os.readLong(); break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
       }
}
