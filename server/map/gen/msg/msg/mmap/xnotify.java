package msg.mmap;

public final class xnotify extends pcore.io.Protocol{
    public static final int TYPE_ID = 9234;

    @Override
    public final int getTypeId() {
        return TYPE_ID;
    }

    public long mapId;
    public byte[] data;

    public xnotify() {
         this.data = new byte[0];

    }

    public xnotify(long mapId , byte[] data ) {
        this.mapId = mapId;
         this.data = data;
         
    }


    @Override
    public String toString() {
        StringBuilder _sb_ = new StringBuilder();
        _sb_.append(this.getClass().getName());
         _sb_.append("[");
         _sb_.append(getTypeId());
         _sb_.append("]");
         
        _sb_.append("{");
        _sb_.append("mapId").append(':').append(mapId).append(',');
        _sb_.append("data").append(':').append(msg.Extensions.tostring_array_byte(data)).append(',');

        _sb_.append("}");
        return _sb_.toString();
    }

    @Override
    public void marshal(pcore.marshal.Octets os) {
        os.writeLong(mapId);
        msg.Extensions.marshal_array_byte(data,os);

    }

    @Override
    public void unmarshal(pcore.marshal.Octets os) {
        mapId = os.readLong();
        data = msg.Extensions.unmarshal_array_byte(os);

    }
}
