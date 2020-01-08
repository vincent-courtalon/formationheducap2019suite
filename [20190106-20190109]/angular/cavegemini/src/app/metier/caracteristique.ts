import { Vin } from './vin';

export class Caracteristique {

    constructor (public id: number, 
                 public libelle:string,
                 public vins?: Vin[]) {}
}