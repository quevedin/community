object FrmConfig: TFrmConfig
  Left = 0
  Top = 0
  Caption = 'Configuracion'
  ClientHeight = 232
  ClientWidth = 331
  Color = clWhite
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label4: TLabel
    Left = 17
    Top = 57
    Width = 131
    Height = 24
    Caption = 'C'#243'digo centro'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -21
    Font.Name = 'Arial'
    Font.Style = []
    ParentFont = False
    Transparent = True
  end
  object Label5: TLabel
    Left = 17
    Top = 92
    Width = 129
    Height = 24
    Caption = 'C'#243'digo tienda'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -21
    Font.Name = 'Arial'
    Font.Style = []
    ParentFont = False
    Transparent = True
  end
  object Label6: TLabel
    Left = 17
    Top = 127
    Width = 37
    Height = 24
    Caption = 'Pos'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -21
    Font.Name = 'Arial'
    Font.Style = []
    ParentFont = False
    Transparent = True
  end
  object LabelTitulo: TLabel
    Left = 0
    Top = 0
    Width = 331
    Height = 35
    Align = alTop
    Alignment = taCenter
    AutoSize = False
    Caption = 'Configuraci'#243'n TEF_WINCOR'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -16
    Font.Name = 'Tahoma'
    Font.Style = [fsBold]
    ParentFont = False
    Transparent = True
    Layout = tlCenter
  end
  object EditCodigoCentro: TRzEdit
    Left = 154
    Top = 57
    Width = 158
    Height = 26
    CharCase = ecUpperCase
    DisabledColor = clWindow
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -16
    Font.Name = 'Arial'
    Font.Style = []
    FocusColor = 8454143
    FrameColor = 10382147
    FrameVisible = True
    ParentFont = False
    TabOrder = 0
  end
  object EditCodigoTienda: TRzEdit
    Left = 154
    Top = 92
    Width = 158
    Height = 26
    CharCase = ecUpperCase
    DisabledColor = clWindow
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -16
    Font.Name = 'Arial'
    Font.Style = []
    FocusColor = 8454143
    FrameColor = 10382147
    FrameVisible = True
    ParentFont = False
    TabOrder = 1
  end
  object EditPos: TRzEdit
    Left = 153
    Top = 127
    Width = 159
    Height = 26
    CharCase = ecUpperCase
    DisabledColor = clWindow
    Font.Charset = DEFAULT_CHARSET
    Font.Color = 12670976
    Font.Height = -16
    Font.Name = 'Arial'
    Font.Style = []
    FocusColor = 8454143
    FrameColor = 10382147
    FrameVisible = True
    ParentFont = False
    TabOrder = 2
  end
  object RzPanel15: TRzPanel
    Left = 0
    Top = 35
    Width = 331
    Height = 6
    Align = alTop
    BiDiMode = bdLeftToRight
    BorderOuter = fsNone
    BorderSides = []
    BorderColor = clWhite
    BorderHighlight = clWhite
    BorderShadow = clWhite
    Color = clWhite
    Ctl3D = True
    FlatColor = clWhite
    GradientColorStyle = gcsCustom
    GradientColorStop = 13546913
    GradientDirection = gdHorizontalCenter
    GridColor = clWhite
    ParentBiDiMode = False
    ParentCtl3D = False
    TabOrder = 3
    VisualStyle = vsGradient
  end
  object RzPanel1: TRzPanel
    Left = 0
    Top = 174
    Width = 331
    Height = 58
    Align = alBottom
    BiDiMode = bdLeftToRight
    BorderOuter = fsNone
    BorderSides = []
    BorderColor = clWhite
    BorderHighlight = clWhite
    BorderShadow = clWhite
    Color = clWhite
    Ctl3D = True
    FlatColor = clWhite
    GradientColorStyle = gcsCustom
    GradientColorStop = 13546913
    GradientDirection = gdHorizontalCenter
    GridColor = clWhite
    ParentBiDiMode = False
    ParentCtl3D = False
    TabOrder = 4
    VisualStyle = vsClassic
    DesignSize = (
      331
      58)
    object btnAceptar: TRzBitBtn
      AlignWithMargins = True
      Left = 19
      Top = 8
      Width = 129
      Height = 44
      Margins.Top = 0
      Margins.Right = 8
      Margins.Bottom = 0
      Default = True
      FrameColor = 10382147
      Anchors = [akRight, akBottom]
      Caption = 'Salvar'
      Color = clNone
      Font.Charset = DEFAULT_CHARSET
      Font.Color = 12670976
      Font.Height = -16
      Font.Name = 'Arial'
      Font.Style = []
      HotTrack = True
      ParentFont = False
      ParentShowHint = False
      ShowHint = False
      TabOrder = 0
      OnClick = btnAceptarClick
      DisabledIndex = 5
      ImageIndex = 20
    end
    object btnCancelar: TRzBitBtn
      AlignWithMargins = True
      Left = 185
      Top = 8
      Width = 129
      Height = 44
      Margins.Top = 0
      Margins.Right = 8
      Margins.Bottom = 0
      Default = True
      FrameColor = 10382147
      Anchors = [akRight, akBottom]
      Caption = 'Cerrar'
      Color = clNone
      Font.Charset = DEFAULT_CHARSET
      Font.Color = 12670976
      Font.Height = -16
      Font.Name = 'Arial'
      Font.Style = []
      HotTrack = True
      ParentFont = False
      ParentShowHint = False
      ShowHint = False
      TabOrder = 1
      OnClick = btnCancelarClick
      DisabledIndex = 5
      ImageIndex = 20
    end
  end
  object RzPanel2: TRzPanel
    Left = 0
    Top = 168
    Width = 331
    Height = 6
    Align = alBottom
    BiDiMode = bdLeftToRight
    BorderOuter = fsNone
    BorderSides = []
    BorderColor = clWhite
    BorderHighlight = clWhite
    BorderShadow = clWhite
    Color = clWhite
    Ctl3D = True
    FlatColor = clWhite
    GradientColorStyle = gcsCustom
    GradientColorStop = 13546913
    GradientDirection = gdHorizontalCenter
    GridColor = clWhite
    ParentBiDiMode = False
    ParentCtl3D = False
    TabOrder = 5
    VisualStyle = vsGradient
  end
end