//Christopher Liebsch
public class Monster implements IMonster {
    final String name; //unchangable
    final int lp_max; //unchangable, min 1, default lp
    private int lp; // >=0
    public float weight; // kilogram, more than 0,1 , unchangeable
    final int base_attack;
    //public int cur_attack;
    public float attack_multiplier;

    public Monster (String name, int lp, float weight, int base_attack, float mult){
        this.name=name;
        if(lp<1){
            this.lp=1;
            this.lp_max=1;
        }else {
            this.lp = lp;
            this.lp_max=lp;
        }
        if (weight>=0.1){
            this.weight=weight;
        }else {
            this.weight= (float) 0.1;
            //System.out.println("Weight is set to 0.1 kg, because your value is to low!");
        }
        this.base_attack=base_attack;
        if(mult>=-2.0 && mult<=5.0) {
            this.attack_multiplier = mult;
        }else if(mult>5){
            this.attack_multiplier=5;
        }else {
            this.attack_multiplier=-2;
        }
        System.out.println(mult);
    }
    @java.lang.Override
    public String getName() {
        return name;
    }

    @java.lang.Override
    public int getHealth() {
        return lp;
    }

    @java.lang.Override
    public int getMaxHealth() {
        return lp_max;
    }

    @java.lang.Override
    public boolean isAlive() {
        return lp > 0;
    }

    @java.lang.Override
    public void receiveDamage(int damage){
        if (this.weight>13.37){
            damage= damage- (damage/5);
        }
        if(damage>=lp){
            lp=0;   
        } else {
            lp-=damage;
        }
        if(lp>lp_max){
            lp=lp_max;
        } else if (lp<0) {
            lp=0;
        }

    }

    @java.lang.Override
    public int getBaseAttack() {
        return base_attack;
    }

    @java.lang.Override
    public int getAttack() {
        return (int)(base_attack*attack_multiplier);
    }

    @java.lang.Override
    public float getWeight() {
        return weight;
    }

    @java.lang.Override
    public void setAttackMultiplier(float multiplier) {
        if(multiplier>=-2.0 && multiplier<=5.0) {
            this.attack_multiplier = multiplier;
        }else if(multiplier>5){
            this.attack_multiplier=5;
        }else {
            this.attack_multiplier=-2;
        }
    }

}
