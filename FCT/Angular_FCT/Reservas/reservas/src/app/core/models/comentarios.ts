export class Comentarios {
  private _usuario_id: Number;
  private _fecha_publicacion: Date;
  private _texto: String;


  constructor(id_user: number) {
    this._usuario_id = id_user;
    this._fecha_publicacion = new Date();
    this._texto = new String();

  }

  toJSON() {
    return {
      id_usuario: this.usuario_id,
      fecha_publicacion: this.fecha_publicacion,
      texto: this._texto
    };
  }
/**
   * Getter usuario_id
   * @return {Number}
   */
public get usuario_id(): Number {
    return this._usuario_id;
  }

  /**
   * Getter fecha_publicacion
   * @return {Date}
   */
  public get fecha_publicacion(): Date {
    return this._fecha_publicacion;
  }
   /**
   * Getter comentario
   * @return {String}
   */
   public get texto(): String{
    return this._texto;
  }


  /**
   * Setter usuario_id
   * @param {Number} value
   */
  public set usuario_id(value: Number) {
    this._usuario_id = value;
  }

  /**
   * Setter fecha_Entrada
   * @param {Date} value
   */
  public set fecha_publicacion(value: Date) {
    this._fecha_publicacion = value;
  }

  /**
   * Getter comentario
   * @return {String}
   */
  public set texto(value:String){
        this._texto=value;
  }
}
