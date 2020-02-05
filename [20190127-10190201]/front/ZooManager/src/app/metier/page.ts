export class Page<T> {
    constructor(public content: T[],
        public totalElements: number,
        public number : number,
        public size : number,
        public totalPages : number,
        public numberOfElements : number,
        public first : boolean,
        public last : boolean,
        public empty : boolean) {}
// fonction utilitaire, genere une page vide du type voulu    
public static emptyPage<T>() : Page<T> {
return new Page<T>([], 0, 0, 5, 1, 0, true, true, true);
}
}