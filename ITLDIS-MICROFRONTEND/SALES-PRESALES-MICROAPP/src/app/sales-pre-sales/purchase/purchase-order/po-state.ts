import { State, Action, StateContext } from '@ngxs/store';

// This is an interface that is part of your domain model
export interface ZebraFood {
  name: string;
  hay: number;
  carrots: number;
}
export interface TigerFood {
  name: string;
  meat: number;
}

// naming your action metadata explicitly makes it easier to understand what the action
// is for and makes debugging easier.
export class FeedZebra {
  static readonly type = '[Zoo] FeedZebra';
  constructor(public zebraToFeed: ZebraFood) { }
}

export class FeedTiger {
  static readonly type = '[Zoo] FeedTiger';
  constructor(public tigerToFeed: TigerFood) { }
}

export interface ZooStateModel {
  zebraFood: ZebraFood[];
  tigerFood: TigerFood[]
}

@State<ZooStateModel>({
  name: 'zoo',
  defaults: {
    zebraFood: [],
    tigerFood: []
  }
})
export class ZooState {
  @Action(FeedZebra)
  feedZebra(ctx: StateContext<ZooStateModel>, action: FeedZebra) {
    console.log("action ", action);
    const state = ctx.getState();
    ctx.setState({
      ...state,
      zebraFood: [
        ...state.zebraFood,
        // this is the new ZebraFood instance that we add to the state
        action.zebraToFeed,
      ]
    });
  }

  @Action(FeedTiger)
  feedTiger(ctx: StateContext<ZooStateModel>, action: FeedTiger) {
    console.log("action ", action);
    const state = ctx.getState();
    ctx.setState({
      ...state,
      tigerFood: [
        ...state.tigerFood,
        // this is the new ZebraFood instance that we add to the state
        action.tigerToFeed,
      ]
    });
  }
}