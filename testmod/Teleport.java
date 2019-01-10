package testmod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class Teleport extends Teleporter
{
	private final WorldServer world;
	private double x,y,z;

	public Teleport(WorldServer world, double x, double y, double z)
	{
		super(world);
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void placeInPortal(Entity entity, float rotationYaw) 
	{
		this.world.getBlockState(new BlockPos((int)this.x,(int)this.y,(int)this.z));
		entity.setPosition(x, y, z);
		entity.motionX = 0f;
		entity.motionY = 0f;
		entity.motionZ = 0f;
	}
	
	public static void TeleportDimension(EntityPlayer player, int dimensionID, double x, double y, double z) 
	{
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldserver = server.getWorld(dimensionID);
		
		if(server == null || worldserver == null) throw new IllegalArgumentException("Dimension "+dimensionID+" dose not exist!");
		if(oldDimension == dimensionID) throw new IllegalArgumentException("You cannot teleport to 'same' dimension");
		
		worldserver.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimensionID, new Teleport(worldserver, x,y,z));
		player.setPositionAndUpdate(x, y, z);
		
		
	}

}
