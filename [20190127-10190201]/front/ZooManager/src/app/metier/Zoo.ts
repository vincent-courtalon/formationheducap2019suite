import { Animal } from './Animal';

export class Zoo {
    constructor(public id: number, public nom: string, public ville: string, public animaux?: Animal[]){}
}