import { Terroir } from './terroir';
import { Caracteristique } from './caracteristique';

export class Vin {
    /*public id : number;

    constructor(id : number) {
        this.id = id;
    }*/
    constructor (   public id: number,
                    public nom: string,
                    public annee: number,
                    public terroir?: Terroir,
                    public caracteristiques?: Caracteristique[]) {}


    
    
}