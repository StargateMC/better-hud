package jobicade.betterhud.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessageConquestState implements IMessage {
    public String state;

    public MessageConquestState() {}
    public MessageConquestState(String state) {
        this.state = state;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.state = ByteBufUtils.readUTF8String(buf); 
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, state); 
    }
}
