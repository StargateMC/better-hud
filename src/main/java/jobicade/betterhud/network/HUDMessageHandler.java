package jobicade.betterhud.network;

import jobicade.betterhud.BetterHud;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HUDMessageHandler implements IMessageHandler<HUDMessage, IMessage> {
    @Override
    public IMessage onMessage(HUDMessage message, MessageContext ctx) {
        switch (message.id) {
            case 1:
                BetterHud.renderLocation = message.state;
                break;
            case 2:
                BetterHud.renderConquestState = message.state;
                break;
            case 3:
                BetterHud.renderWorldSecurity = message.state;
                break;           
            case 4:
                BetterHud.renderConquestPoints = message.state;
                break;              
            case 5:
                BetterHud.renderRespawnLocation = message.state;
                break;     
            case 6:
                BetterHud.renderSystemImportance = message.state;
                break;
            case 7:
                BetterHud.renderFactionState = message.state;
                break;
            case 8:
                BetterHud.renderContestableState = message.state;
                break;
        }
        return null;
    }
}
