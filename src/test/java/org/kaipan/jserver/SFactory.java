package org.kaipan.jserver;

import org.kaipan.jserver.socket.core.Server;
import org.kaipan.jserver.socket.protocol.websocket.WsConfig;
import org.kaipan.jserver.socket.server.ServerFactory;

public class SFactory
{
	public static void main(String[] _args)
    {
		String[] args = {"DefaultQueueServer", null};
		
		String name = "DefaultQueueServer";
		String path = null;
		
		switch ( args.length ) {
			case 1:
				name = args[0];
				break;
			case 2:
				name = args[0];
				path = args[1];
				break;
			default:
				throw new IllegalArgumentException("Invalid operating parameters");
		}
		
		String[] parts = {ServerFactory.class.getPackage().getName(), ".", name};
		
		Server server = ServerFactory.create(String.join("", parts), new WsConfig(), "queue-server.properties", path);
        server.start();
    }
}
