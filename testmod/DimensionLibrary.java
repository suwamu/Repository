package testmod;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class DimensionLibrary extends WorldProvider{

	public static final int LIBRARYID = 2;

	@Override
	public BiomeProvider getBiomeProvider() {
		
		return biomeProvider;

		//return  new SampleBiomeProvider(world, Biomes.MESA, Biomes.FOREST);
	}

	@Override
	public DimensionType getDimensionType() {
		return DimensionInit.LIBRARY;

	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorLibrary(world, LIBRARYID);
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	public boolean hasSkyLight() {
		return true;
	}
	
	
}
