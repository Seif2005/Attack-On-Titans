package game.engine.weapons;

import java.util.PriorityQueue;

import game.engine.titans.Titan;

public class SniperCannon extends Weapon
{
	public static final int WEAPON_CODE = 2;

	public SniperCannon(int baseDamage)
	{
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans)
	{
		Titan closestTitan = laneTitans.peek();
		int resourcesGathered = 0;

		if (closestTitan != null)
		{
			resourcesGathered += this.attack(closestTitan);

			if (closestTitan.isDefeated())
			{
				laneTitans.remove(closestTitan);
			}
		}

		return resourcesGathered;
	}

}
