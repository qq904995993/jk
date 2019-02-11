package instance.game.character;

import instance.game.weapon.KnifeBehavior;
import instance.game.weapon.WeaponBehavior;

/**
 *  不同角色通过使用不同武器去战斗
 */
public class Character {

    WeaponBehavior weaponBehavior;

    public void fight() {
        if(weaponBehavior == null) {
            System.out.println("no weapon,user body to fight!");
            return;
        }
        weaponBehavior.useWeapon();
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

}
