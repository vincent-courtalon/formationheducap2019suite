import { JeuxVideo } from './jeux-video';

export class PlateForme {
    constructor(public id : number,
                public nom: string,
                public marque: string,
                public jeuxVideos? : JeuxVideo[]) {}
}