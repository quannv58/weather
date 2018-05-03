import React, { Component } from 'react';
import {
    ScrollView
} from 'react-native';
import axios from 'axios';
import AlbumDetail from './AlbumDetail';

export default class Header extends Component {
    state = { albums: [] };
    componentWillMount() {
        axios.get('https://rallycoding.herokuapp.com/music_albums').then(response => this.setState({ albums: response.data }));
    }

    renderAlbums() {
        return this.state.albums.map(album => <AlbumDetail record={album} />);
    }

    render() {
        return (
            <ScrollView>
                {this.renderAlbums()}
            </ScrollView>
        );
    }
}
