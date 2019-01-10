package testmod.BiomeProvider;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerBiomesCustom2 extends GenLayer {

	private Biome[] allowedBiomes;
			

	public GenLayerBiomesCustom2(long seed,Biome[] biomes) 
	{
		super(seed);
		this.allowedBiomes = biomes;
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
