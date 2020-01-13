import { Editeur } from './editeur';
import { PlateForme } from './plate-forme';
import { Genre } from './genre';

export class JeuxVideo {
    constructor(public id : number,
                public nom: string,
                public dateSortie: Date,
                public editeur? : Editeur,
                public genres ? : Genre[],
                public plateFormes ? : PlateForme[]
                ) {}
}