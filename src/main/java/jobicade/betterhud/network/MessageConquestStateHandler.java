package jobicade.betterhud.network;

import jobicade.betterhud.BetterHud;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageConquestStateHandler implements IMessageHandler<MessageConquestState, IMessage> {
    @Override
    public IMessage onMessage(MessageConquestState message, MessageContext ctx) {
        System.out.println("RECEIVED: state id " + message.state + ", faction " + message.faction);
        BetterHud.renderConquestFaction = message.faction;
        BetterHud.renderConquestState = message.state;
        return null;
    }
}
