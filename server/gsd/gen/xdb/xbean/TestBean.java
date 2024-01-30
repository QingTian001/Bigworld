package xbean;

@SuppressWarnings("unchecked")
public final class TestBean extends  pcore.db.Bean  {
       public static TestBean newBean() { return new TestBean(); }
       public static final int TYPE_ID = 0;
       @Override
       public final int getTypeId() {
           return TYPE_ID;
       }

       protected long t1;
       protected int t2;
       protected String t3;
       protected xbean.TestInnerBean t4;
       protected pcore.db.collection.XList<xbean.TestInnerBean> t5;
       protected pcore.db.collection.XMap<Integer,xbean.TestInnerBean> t6;
       protected pcore.db.collection.XSet<Integer> t7;

       public TestBean() {
            this.t3 = "";
            this.t4 = new xbean.TestInnerBean();
            this.t5 = pcore.db.collection.Factory.newList(true);
            this.t6 = pcore.db.collection.Factory.newMap(true);
            this.t7 = pcore.db.collection.Factory.newSet();
       }

       public TestBean(long t1 , int t2 , String t3 , xbean.TestInnerBean t4 , pcore.db.collection.XList<xbean.TestInnerBean> t5 , pcore.db.collection.XMap<Integer,xbean.TestInnerBean> t6 , pcore.db.collection.XSet<Integer> t7 ) {
           this.t1 = t1; 
           this.t2 = t2; 
           this.t3 = t3; 
           this.t4 = t4; 
           this.t5 = t5; 
           this.t6 = t6; 
           this.t7 = t7; 
       }

       @Override
       public TestBean copy() {
           return new TestBean(getT1(), getT2(), getT3(), getT4().copy(), (pcore.db.collection.XList<xbean.TestInnerBean>)getT5().copy(), (pcore.db.collection.XMap<Integer,xbean.TestInnerBean>)getT6().copy(), (pcore.db.collection.XSet<Integer>)getT7().copy());
       }

       @Override
       public String toString() {
           StringBuilder sb = new StringBuilder();
           sb.append(this.getClass().getName()).append("{");
           sb.append("t1").append(':').append(getT1()).append(',');
           sb.append("t2").append(':').append(getT2()).append(',');
           sb.append("t3").append(':').append(getT3()).append(',');
           sb.append("t4").append(':').append(xbean.Extensions.tostring_xbean_TestInnerBean(getT4())).append(',');
           sb.append("t5").append(':').append(xbean.Extensions.tostring_list_xbean_TestInnerBean(getT5())).append(',');
           sb.append("t6").append(':').append(xbean.Extensions.tostring_map_int_xbean_TestInnerBean(getT6())).append(',');
           sb.append("t7").append(':').append(xbean.Extensions.tostring_set_int(getT7())).append(',');
           sb.append("}");
           return sb.toString();
       }

       private final static class _Log_t1 extends pcore.db.FieldLog<TestBean> {
            _Log_t1(TestBean _this, long value) {
                super(_this);
                this.value = value;
            }

            long value;

            @Override
            public void commit() {
                this.bean.t1 = this.value;
            }
       }

       public long getT1() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return t1;
            _Log_t1 log = txn.getField(objectId + 2);
            return log == null ? t1 : log.value;
       }

       public void setT1(long _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_t1 log = txn.getFieldAtCurrentSavePoint(objectId + 2);
            if (log == null) txn.putField(objectId + 2, new _Log_t1(this, _value_));
            else log.value = _value_;
       }

       private final static class _Log_t2 extends pcore.db.FieldLog<TestBean> {
            _Log_t2(TestBean _this, int value) {
                super(_this);
                this.value = value;
            }

            int value;

            @Override
            public void commit() {
                this.bean.t2 = this.value;
            }
       }

       public int getT2() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return t2;
            _Log_t2 log = txn.getField(objectId + 3);
            return log == null ? t2 : log.value;
       }

       public void setT2(int _value_) {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_t2 log = txn.getFieldAtCurrentSavePoint(objectId + 3);
            if (log == null) txn.putField(objectId + 3, new _Log_t2(this, _value_));
            else log.value = _value_;
       }

       private final static class _Log_t3 extends pcore.db.FieldLog<TestBean> {
            _Log_t3(TestBean _this, String value) {
                super(_this);
                this.value = value;
            }

            String value;

            @Override
            public void commit() {
                this.bean.t3 = this.value;
            }
       }

       public String getT3() {
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            if (txn == null) return t3;
            _Log_t3 log = txn.getField(objectId + 4);
            return log == null ? t3 : log.value;
       }

       public void setT3(String _value_) {
            if (_value_ == null) throw new NullPointerException();
            pcore.db.Transaction txn = pcore.db.Transaction.get();
            _Log_t3 log = txn.getFieldAtCurrentSavePoint(objectId + 4);
            if (log == null) txn.putField(objectId + 4, new _Log_t3(this, _value_));
            else log.value = _value_;
       }

       public xbean.TestInnerBean getT4() { return t4; } 

       public pcore.db.collection.XList<xbean.TestInnerBean> getT5() { return t5; } 

       public pcore.db.collection.XMap<Integer,xbean.TestInnerBean> getT6() { return t6; } 

       public pcore.db.collection.XSet<Integer> getT7() { return t7; } 



       @Override
       protected void setChildrenRootInTxn(pcore.db.Transaction txn, long root) {
           Helper.setRootInTxn(this.t4, txn, root);
           Helper.setRootInTxn(this.t5, txn, root);
           Helper.setRootInTxn(this.t6, txn, root);
           Helper.setRootInTxn(this.t7, txn, root);
       }

       @Override
       protected void applyChildrenRootInTxn(long root) {
           Helper.applyRootInTxn(this.t4, root);
           Helper.applyRootInTxn(this.t5, root);
           Helper.applyRootInTxn(this.t6, root);
           Helper.applyRootInTxn(this.t7, root);
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
           os.writeSize(7);
           var _os = pcore.marshal.Octets.alloc();
           os.writeCompactUint(pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)); os.writeLong(t1);
           os.writeCompactUint(pcore.marshal.Tag.INT | (2 << pcore.marshal.Tag.TAG_SHIFT)); os.writeInt(t2);
           os.writeCompactUint(pcore.marshal.Tag.STRING | (3 << pcore.marshal.Tag.TAG_SHIFT)); os.writeString(t3);
           os.writeCompactUint(pcore.marshal.Tag.BEAN | (4 << pcore.marshal.Tag.TAG_SHIFT)); { _os.clear(); xbean.Extensions.marshal_compatible_xbean_TestInnerBean(t4,_os); os.writeOctets(_os);}
           os.writeCompactUint(pcore.marshal.Tag.LIST | (5 << pcore.marshal.Tag.TAG_SHIFT)); { _os.clear(); xbean.Extensions.marshal_compatible_list_xbean_TestInnerBean(t5,_os); os.writeOctets(_os);}
           os.writeCompactUint(pcore.marshal.Tag.MAP | (6 << pcore.marshal.Tag.TAG_SHIFT)); { _os.clear(); xbean.Extensions.marshal_compatible_map_int_xbean_TestInnerBean(t6,_os); os.writeOctets(_os);}
           os.writeCompactUint(pcore.marshal.Tag.SET | (7 << pcore.marshal.Tag.TAG_SHIFT)); { _os.clear(); xbean.Extensions.marshal_compatible_set_int(t7,_os); os.writeOctets(_os);}
           pcore.marshal.Octets.free(_os);
       }

       @Override
       public void unmarshalCompatible(pcore.marshal.Octets os) {
           var _os = pcore.marshal.Octets.alloc();
           for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
               final int _id_ = os.readCompactUint();
               switch (_id_) {
                   case ( pcore.marshal.Tag.LONG | (1 << pcore.marshal.Tag.TAG_SHIFT)): t1 = os.readLong(); break;
                   case ( pcore.marshal.Tag.INT | (2 << pcore.marshal.Tag.TAG_SHIFT)): t2 = os.readInt(); break;
                   case ( pcore.marshal.Tag.STRING | (3 << pcore.marshal.Tag.TAG_SHIFT)): t3 = os.readString(); break;
                   case ( pcore.marshal.Tag.BEAN | (4 << pcore.marshal.Tag.TAG_SHIFT)): { _os.clear(); os.readOctets(_os); t4 = xbean.Extensions.unmarshal_compatible_xbean_TestInnerBean(_os); } break;
                   case ( pcore.marshal.Tag.LIST | (5 << pcore.marshal.Tag.TAG_SHIFT)): { _os.clear(); os.readOctets(_os); t5 = xbean.Extensions.unmarshal_compatible_list_xbean_TestInnerBean(_os); } break;
                   case ( pcore.marshal.Tag.MAP | (6 << pcore.marshal.Tag.TAG_SHIFT)): { _os.clear(); os.readOctets(_os); t6 = xbean.Extensions.unmarshal_compatible_map_int_xbean_TestInnerBean(_os); } break;
                   case ( pcore.marshal.Tag.SET | (7 << pcore.marshal.Tag.TAG_SHIFT)): { _os.clear(); os.readOctets(_os); t7 = xbean.Extensions.unmarshal_compatible_set_int(_os); } break;
                   default: pcore.marshal.Tag.skipUnknownField(_id_, os);
               }
           }
          pcore.marshal.Octets.free(_os);
       }
}
