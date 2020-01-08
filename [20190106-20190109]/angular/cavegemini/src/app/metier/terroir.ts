import { Vin } from './vin';

export class Terroir {

    constructor(public id: number,
                public nom:string,
                public vins?: Vin[]) {}
}