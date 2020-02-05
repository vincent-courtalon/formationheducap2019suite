import { Zoo } from './Zoo';

export class Animal {
    constructor(public id: number, public nom: string, public espece: string, public zoo?: Zoo) {}
}