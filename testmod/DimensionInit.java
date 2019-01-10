package testmod;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionInit {
	
	public static final DimensionType LIBRARY = DimensionType.register("Library", "_library", DimensionLibrary.LIBRARYID, DimensionLibrary.class, false);
	
	public static void registerDimension()
	{
		DimensionManager.registerDimension(DimensionLibrary.LIBRARYID, LIBRARY);
	}

}
