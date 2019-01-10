package testmod.BiomeProvider;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesCustom extends GenLayer {

	private Biome[] allowedBiomes;
	
	public GenLayerBiomesCustom(long seed) 
	{
		super(seed);		
		Biome biome[] = {Biomes.MESA};
		this.allowedBiomes = biome;
	}

	public GenLayerBiomesCustom(long seed, Biome... Biomes)
	{
		super(seed);
		this.allowedBiomes = Biomes;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz = 0; dz < depth; dz++) {
			for (int dx = 0; dx < width; dx++) {
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = Biome.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
			}
		}
		return dest;
	}
}

