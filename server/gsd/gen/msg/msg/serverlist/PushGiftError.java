package msg.serverlist;

public final class PushGiftError  extends ResultError {

    public static final int TYPE_ID = 12;
    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }


    public java.util.List<Integer> notExistsPushGifts;

    public PushGiftError() {
         this.notExistsPushGifts = pcore.collection.Factory.newList();

    }

    public PushGiftError(java.util.List<Integer> notExistsPushGifts ) {
        this.notExistsPushGifts = notExistsPushGifts;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         
        _sb_.append("{");
        _sb_.append("notExistsPushGifts").append(':').append(msg.Extensions.tostring_list_int(notExistsPushGifts)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        super.marshal(os);
        msg.Extensions.marshal_list_int(notExistsPushGifts,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        super.unmarshal(os);
        notExistsPushGifts = msg.Extensions.unmarshal_list_int(os);

    }

    @Override
    public void marshalCompatible(pcore.marshal.Octets os) {
        os.writeSize(1);
        os.writeCompactUint(pcore.marshal.Tag.LIST | (1540 << pcore.marshal.Tag.TAG_SHIFT));  { var _oldOs = os; os = new pcore.marshal.Octets(); msg.Extensions.marshal_compatible_list_int(notExistsPushGifts,os); _oldOs.writeOctets(os); os = _oldOs;}  


    }

    @Override
    public void unmarshalCompatible(pcore.marshal.Octets os) {
            for (int _var_num_ = os.readSize(); _var_num_-- > 0; ) {
                final int _id_ = os.readCompactUint();
                switch (_id_) {
                case (pcore.marshal.Tag.LIST | (1540 << pcore.marshal.Tag.TAG_SHIFT)):  { var _oldOs = os; os = os.readOctets(); notExistsPushGifts = msg.Extensions.unmarshal_compatible_list_int(os); os = _oldOs; break; }   
                 
                default: pcore.marshal.Tag.skipUnknownField(_id_, os);
            }
        }
    }
}
