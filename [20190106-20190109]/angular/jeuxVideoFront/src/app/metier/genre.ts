import { JeuxVideo } from './jeux-video';

export class Genre {
    constructor(public id: number,
                public libelle: string,
                public jeuxVideos? : JeuxVideo[]){}
}